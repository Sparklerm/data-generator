package com.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.generator.model.domain.Tables;

import java.util.List;

/**
 * @author Sparkler
 * @description 针对表【TABLES】的数据库操作Service
 * @createDate 2022-11-09 16:46:21
 */
public interface TablesService extends IService<Tables> {
    /**
     * 查询数据库所有表信息
     *
     * @return List<TablesDO>
     */
    List<Tables> selectAllTableInfo();
}
