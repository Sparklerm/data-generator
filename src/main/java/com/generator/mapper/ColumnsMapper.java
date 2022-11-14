package com.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.generator.model.domain.Columns;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Sparkler
 * @description 针对表【COLUMNS】的数据库操作Mapper
 * @createDate 2022-11-09 16:46:21
 * @Entity com.sparkler.generator.domain.Columns
 */
@Mapper
public interface ColumnsMapper extends BaseMapper<Columns> {
    /**
     * 查询表的所有字段信息
     *
     * @param tableName 表名
     * @return List<ColumnsDO>
     */
    List<Columns> selectColumnsByTableName(@Param("tableName") String tableName);
}




