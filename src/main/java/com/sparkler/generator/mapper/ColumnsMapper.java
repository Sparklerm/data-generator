package com.sparkler.generator.mapper;

import com.sparkler.generator.model.dto.ColumnsDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Sparkler
 * @description 针对表【COLUMNS】的数据库操作Mapper
 * @createDate 2022-11-01 16:36:22
 * @Entity com.sparkler.model.domain.Columns
 */
@Mapper
public interface ColumnsMapper {

    /**
     * 查询表的所有字段信息
     *
     * @param tableName 表名
     * @return List<ColumnsDO>
     */
    List<ColumnsDO> selectColumnsByTableName(@Param("tableName") String tableName);
}
