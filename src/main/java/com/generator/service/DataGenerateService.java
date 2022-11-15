package com.generator.service;

import com.generator.model.qo.ColumnInfoQo;
import com.generator.model.qo.TableInfoQo;

import java.util.Map;

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
    void batchInsertData(String tableName, Map<ColumnInfoQo, String> columnInfo, Integer pkAutoIncrement, Integer records);

    /**
     * 多线程批量插入数据
     *
     * @param tableInfoQo
     * @param columnInfos
     * @param threadSize
     * @param onceInsertRecode
     */
    void toThreadBatchInsert(TableInfoQo tableInfoQo, Map<ColumnInfoQo, String> columnInfos, int threadSize, int onceInsertRecode);
}
