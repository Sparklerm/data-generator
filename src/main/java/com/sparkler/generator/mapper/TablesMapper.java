package com.sparkler.generator.mapper;

import com.sparkler.generator.model.domain.Tables;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Sparkler
 * @description 针对表【TABLES】的数据库操作Mapper
 * @createDate 2022-11-09 16:46:21
 * @Entity com.sparkler.generator.domain.Tables
 */
@Mapper
public interface TablesMapper extends BaseMapper<Tables> {
    /**
     * 查询数据库所有表信息
     *
     * @return List<TablesDO>
     */
    List<Tables> selectAllTableInfo();
}




