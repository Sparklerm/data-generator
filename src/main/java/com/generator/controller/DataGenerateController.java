package com.generator.controller;

import com.generator.model.common.ConfigKey;
import com.generator.model.enums.DbDataTypeMatchEnum;
import com.generator.model.helper.Result;
import com.generator.model.qo.ColumnInfoQo;
import com.generator.model.qo.TableInfoQo;
import com.generator.service.DataGenerateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sparkler
 * @createDate 2022/11/12
 */
@RestController
@Slf4j
@Api(tags = "DataGenerateController")
public class DataGenerateController {

    @Value("${async.executor.thread.core_pool_size}")
    private int corePoolSize;

    @Autowired
    private DataGenerateService dataGenerateService;


    @ApiOperation("生成数据")
    @PostMapping("/api/generate")
    public Result batchInsertData(@RequestBody TableInfoQo tableInfoQo) {
        long start = System.currentTimeMillis();
        List<ColumnInfoQo> columns = tableInfoQo.getColumns();
        Map<ColumnInfoQo, DbDataTypeMatchEnum> columnInfos = new HashMap<>(columns.size());
        for (ColumnInfoQo columnInfo : columns) {
            if ("auto_increment".equals(columnInfo.getExtra())) {
                continue;
            }
            columnInfos.put(columnInfo, DbDataTypeMatchEnum.getDataTypeByDbType(columnInfo.getDataType()));
        }

        int onceInsertRecord = ConfigKey.ONCE_INSERT_RECORDS;

        /**
         * 根据单次插入数据量计算出需要多少次插入
         */
        int needThreadCount = 0;

        /**
         * 批量插入后的剩余数量
         */
        int remainingRecords = 0;


        if (tableInfoQo.getRecords() < onceInsertRecord) {
            needThreadCount = corePoolSize;
            onceInsertRecord = tableInfoQo.getRecords() / needThreadCount;
            remainingRecords = tableInfoQo.getRecords() % corePoolSize;
        } else {
            needThreadCount = tableInfoQo.getRecords() / onceInsertRecord;

            remainingRecords = tableInfoQo.getRecords() % onceInsertRecord;
        }
        /**
         * 根据当前线程池的线程数计算出需要进行多线程批量插入的次数；
         * 使用循环批量插入目的：
         *  1. 是防止数据量过大，生产线程过多导致OOM；
         *  2. 线程池配置等待队列过小导致线程任务丢弃；
         */
        int poolCount = needThreadCount / corePoolSize;
        if (poolCount == 0) {
            dataGenerateService.toThreadBatchInsert(tableInfoQo, columnInfos, needThreadCount, onceInsertRecord);
        } else {
            for (int pool = 0; pool < poolCount; pool++) {
                int threadSize = getThreadSize(needThreadCount, poolCount, pool);
                dataGenerateService.toThreadBatchInsert(tableInfoQo, columnInfos, threadSize, onceInsertRecord);
            }
        }
        if (remainingRecords > 0) {
            dataGenerateService.batchInsertData(tableInfoQo.getTableName(), columnInfos, 0, remainingRecords);
        }

        long endTime = System.currentTimeMillis();
        return Result.success("use time : " + (endTime - start));
    }

    private int getThreadSize(int needThreadCount, int poolCount, int pool) {
        int threadSize = 0;
        if (pool == (poolCount - 1)) {
            threadSize = corePoolSize + (needThreadCount % corePoolSize);
        } else {
            threadSize = corePoolSize;
        }
        return threadSize;
    }
}
