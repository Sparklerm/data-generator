package com.sparkler.generator.mapper;

import com.sparkler.generator.model.dto.TablesDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Sparkler
 * @description 针对表【TABLES】的数据库操作Mapper
 * @createDate 2022-11-01 16:36:22
 * @Entity com.sparkler.model.domain.Tables
 */
@Mapper
public interface TablesMapper {

    /**
     * 查询数据库所有表信息
     *
     * @return List<TablesDO>
     */
    List<TablesDO> selectAllTableInfo();
}
