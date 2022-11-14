package com.generator.service;

import com.generator.model.enums.DbDataTypeMatchEnum;
import com.generator.model.qo.ColumnInfoQo;
import com.generator.model.qo.TableInfoQo;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author Sparkler
 * @createDate 2022/11/12
 */
public interface DataGenerateService {
    /**
     * 单线程插入数据
     *
     * @param tableName
     * @param columnInfo
     * @param pkAutoIncrement
     * @param records
     */
    void batchInsertData(String tableName, Map<ColumnInfoQo, DbDataTypeMatchEnum> columnInfo, Integer pkAutoIncrement, Integer records);

    /**
     * 多线程批量插入数据
     *
     * @param tableName
     * @param columnInfo
     * @param pkAutoIncrement
     * @param records
     * @param countDownLatch
     */
    void threadBatchInsertData(String tableName, Map<ColumnInfoQo, DbDataTypeMatchEnum> columnInfo, Integer pkAutoIncrement, Integer records, CountDownLatch countDownLatch);

    void toThreadBatchInsert(TableInfoQo tableInfoQo, Map<ColumnInfoQo, DbDataTypeMatchEnum> columnInfos, int threadSize, int onceInsertRecode);
}
