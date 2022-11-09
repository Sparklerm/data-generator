package com.sparkler.generator.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sparkler.generator.mapper.ColumnsMapper;
import com.sparkler.generator.mapper.TablesMapper;
import com.sparkler.generator.model.domain.Columns;
import com.sparkler.generator.model.domain.Tables;
import com.sparkler.generator.model.helper.Result;
import com.sparkler.generator.model.vo.TableColumnVO;
import com.sparkler.generator.model.vo.TableInfoVO;
import com.sparkler.generator.service.ColumnsService;
import com.sparkler.generator.service.TablesService;
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
    public Result getTableInfo() {
        try {
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
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }


    @GetMapping("/data_page")
    public Result selectTable(@RequestParam(value = "pageNum", required = false) Integer pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<Tables> tableList = tablesService.selectAllTableInfo();
            return Result.success(new PageInfo(tableList));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/columns")
    public Result getColumnsByTableName(@RequestParam(value = "tableName", required = false) String tableName) {
        try {
            List<Columns> columns = columnsService.selectColumnsByTableName(tableName);
            return Result.success(columns);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }


}
