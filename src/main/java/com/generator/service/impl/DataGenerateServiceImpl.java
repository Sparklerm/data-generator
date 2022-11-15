package com.generator.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.generator.mapper.MySQLDataGenerateMapper;
import com.generator.model.qo.ColumnInfoQo;
import com.generator.model.qo.TableInfoQo;
import com.generator.service.DataGenerateService;
import com.generator.service.DataGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * @author Sparkler
 * @createDate 2022/11/12
 */
@Service
@Slf4j
public class DataGenerateServiceImpl implements DataGenerateService {

    @Autowired
    private MySQLDataGenerateMapper mySQLDataGenerateMapper;

    @Autowired
    private DataGenerator dataGenerator;

    @Override
    public void batchInsertData(String tableName, Map<ColumnInfoQo, String> columnInfos, Integer pkAutoIncrement, Integer records) {
        dataGenerate(tableName, columnInfos, records);
    }

    @Async("asyncServiceExecutor")
    @Override
    public void toThreadBatchInsert(TableInfoQo tableInfoQo, Map<ColumnInfoQo, String> columnInfos, int threadSize, int onceInsertRecode) {
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            try {
                this.dataGenerate(tableInfoQo.getTableName(), columnInfos, onceInsertRecode);
            } finally {
                countDownLatch.countDown();
            }
        }
        try {
            countDownLatch.await();
        } catch (Exception e) {
            log.error("阻塞异常:" + e.getMessage());
        }
    }

    private void dataGenerate(String tableName, Map<ColumnInfoQo, String> columnInfos, Integer records) {
        List<ColumnInfoQo> columns = new ArrayList<>(columnInfos.keySet());
        List<String> columnName = columns.stream().map(ColumnInfoQo::getColumnName).collect(Collectors.toList());
        JSONArray jsonArray = new JSONArray(records);
        for (int i = 0; i < records; i++) {
            JSONObject jsonObject = new JSONObject();
            for (ColumnInfoQo column : columns) {
                if (column.getPrimaryKey() == 1) {
                    jsonObject.put(column.getColumnName(), dataGenerator.pkReturn(columnInfos.get(column)));
                } else {
                    jsonObject.put(column.getColumnName(), dataGenerator.dataReturn(columnInfos.get(column), column.getDataLength()));
                }
            }
            jsonArray.add(jsonObject);
        }

        List<Map<String, Object>> data = new ArrayList<>();
        if (jsonArray.size() > 0) {
            data = (List) jsonArray;
        }
        mySQLDataGenerateMapper.insertData(tableName, columnName, data);
    }


}
