package com.generator.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Sparkler
 * @createDate 2022/11/12
 */
@Mapper
public interface MySQLDataGenerateMapper {
    /**
     * 插入数据
     *
     * @param tableName
     * @param columns
     * @param data
     */
    void insertData(@Param("tableName") String tableName, @Param("columns") List<String> columns, @Param("data") List<Map<String, Object>> data);
}
