package com.generator.controller;

import com.generator.model.domain.Columns;
import com.generator.model.domain.Tables;
import com.generator.model.helper.Result;
import com.generator.model.vo.TableColumnVO;
import com.generator.model.vo.TableInfoVO;
import com.generator.service.ColumnsService;
import com.generator.service.TablesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private TablesService tablesService;

    @Autowired
    private ColumnsService columnsService;


    @GetMapping("/all_table_info")
    public Result<List<TableInfoVO>> getTableInfo() {
        List<Tables> tableList = tablesService.selectAllTableInfo();

        List<TableInfoVO> tableInfoVOList = new ArrayList<>(tableList.size());
        for (Tables tableInfo : tableList) {
            TableInfoVO tableInfoVO = new TableInfoVO();
            List<Columns> columns = columnsService.selectColumnsByTableName(tableInfo.getTableName());
            BeanUtils.copyProperties(tableInfo, tableInfoVO);
            List<TableColumnVO> tableColumnVOList = new ArrayList<>(columns.size());
            for (Columns column : columns) {
                TableColumnVO tableColumnVO = new TableColumnVO();
                BeanUtils.copyProperties(column, tableColumnVO);
                tableColumnVOList.add(tableColumnVO);
            }
            tableInfoVO.setColumns(tableColumnVOList);
            tableInfoVOList.add(tableInfoVO);
        }
        return Result.success(tableInfoVOList);
    }


    @GetMapping("/data_page")
    public Result<PageInfo<Tables>> selectTable(@RequestParam(value = "pageNum", required = false) Integer pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Tables> tableList = tablesService.selectAllTableInfo();
        PageInfo<Tables> pageInfo = new PageInfo<>(tableList);
        return Result.success(pageInfo);
    }

    @GetMapping("/columns")
    public Result<List<Columns>> getColumnsByTableName(@RequestParam(value = "tableName", required = false) String tableName) {
        List<Columns> columns = columnsService.selectColumnsByTableName(tableName);
        return Result.success(columns);
    }


}
