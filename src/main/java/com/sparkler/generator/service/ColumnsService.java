package com.sparkler.generator.service;

import com.sparkler.generator.model.domain.Columns;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Sparkler
 * @description 针对表【COLUMNS】的数据库操作Service
 * @createDate 2022-11-09 16:46:21
 */
public interface ColumnsService extends IService<Columns> {
    /**
     * 查询表的所有字段信息
     *
     * @param tableName 表名
     * @return List<ColumnsDO>
     */
    List<Columns> selectColumnsByTableName(@Param("tableName") String tableName);
}
