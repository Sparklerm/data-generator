package com.sparkler.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sparkler.generator.model.domain.Tables;
import com.sparkler.generator.service.TablesService;
import com.sparkler.generator.mapper.TablesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sparkler
 * @description 针对表【TABLES】的数据库操作Service实现
 * @createDate 2022-11-09 16:46:21
 */
@Service
public class TablesServiceImpl extends ServiceImpl<TablesMapper, Tables> implements TablesService {

    @Autowired
    private TablesMapper tablesMapper;

    @Override
    public List<Tables> selectAllTableInfo() {
        return tablesMapper.selectAllTableInfo();
    }
}




