package com.generator.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.generator.mapper.DataGenerateMapper;
import com.generator.model.common.ConfigKey;
import com.generator.model.enums.DbDataTypeMatchEnum;
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
    private DataGenerateMapper dataGenerateMapper;

    @Autowired
    private DataGenerator dataGenerator;

    @Override
    public void batchInsertData(String tableName, Map<ColumnInfoQo, DbDataTypeMatchEnum> columnInfos, Integer pkAutoIncrement, Integer records) {
        dataGenerate(tableName, columnInfos, records);
    }

    @Override
    @Async("asyncServiceExecutor")
    public void threadBatchInsertData(String tableName, Map<ColumnInfoQo, DbDataTypeMatchEnum> columnInfos, Integer pkAutoIncrement, Integer records, CountDownLatch countDownLatch) {
        try {
            this.dataGenerate(tableName, columnInfos, records);
        } finally {
            countDownLatch.countDown();
        }
    }


    private void dataGenerate(String tableName, Map<ColumnInfoQo, DbDataTypeMatchEnum> columnInfos, Integer records) {
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
        if (jsonArray != null && jsonArray.size() > 0) {
            data = (List) jsonArray;
        }
        dataGenerateMapper.insertData(tableName, columnName, data);
    }

    @Override
    public void toThreadBatchInsert(TableInfoQo tableInfoQo, Map<ColumnInfoQo, DbDataTypeMatchEnum> columnInfos, int threadSize) {
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            this.threadBatchInsertData(tableInfoQo.getTableName(), columnInfos, 0, ConfigKey.ONCE_INSERT_RECORDS, countDownLatch);
        }
        try {
            countDownLatch.await();
        } catch (Exception e) {
            log.error("阻塞异常:" + e.getMessage());
        }
    }
}
