package com.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.generator.mapper.ColumnsMapper;
import com.generator.model.domain.Columns;
import com.generator.service.ColumnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sparkler
 * @description 针对表【COLUMNS】的数据库操作Service实现
 * @createDate 2022-11-09 16:46:21
 */
@Service
public class ColumnsServiceImpl extends ServiceImpl<ColumnsMapper, Columns> implements ColumnsService {

    @Autowired
    private ColumnsMapper columnsMapper;

    @Override
    public List<Columns> selectColumnsByTableName(String tableName) {
        return columnsMapper.selectColumnsByTableName(tableName);
    }
}




