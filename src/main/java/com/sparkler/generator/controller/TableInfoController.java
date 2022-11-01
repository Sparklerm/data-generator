package com.sparkler.generator.controller;

import com.sparkler.generator.mapper.ColumnsMapper;
import com.sparkler.generator.mapper.TablesMapper;
import com.sparkler.generator.model.dto.ColumnsDO;
import com.sparkler.generator.model.dto.TablesDO;
import com.sparkler.generator.model.vo.TableInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sparkler
 * @createDate 2022/11/1
 */
@RestController
@RequestMapping("/api/table")
public class TableInfoController {

    @Autowired
    private TablesMapper tablesMapper;

    @Autowired
    private ColumnsMapper columnsMapper;


    @GetMapping("/all_table_info")
    public List<TableInfoVO> getTableInfo() {
        List<TablesDO> tableList = tablesMapper.selectAllTableInfo();

        List<TableInfoVO> tableInfoVOList = new ArrayList<>(tableList.size());
        for (TablesDO tableInfo : tableList) {
            TableInfoVO tableInfoVO = new TableInfoVO();
            List<ColumnsDO> columns = columnsMapper.selectColumnsByTableName(tableInfo.getTableName());
            BeanUtils.copyProperties(tableInfo, tableInfoVO);
            tableInfoVO.setColumns(columns);
            tableInfoVOList.add(tableInfoVO);
        }
        return tableInfoVOList;
    }
}
