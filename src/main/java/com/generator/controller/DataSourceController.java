package com.generator.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.generator.config.db.DataSourceConfig;
import com.generator.config.db.DynamicDataSource;
import com.generator.model.common.DatabaseConnectUrl;
import com.generator.model.dto.DataSourceInfo;
import com.generator.model.helper.Result;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * @author Sparkler
 * @createDate 2022/11/15
 */
@RestController
@RequestMapping("/api/datasource")
public class DataSourceController {

    @PostMapping("/add")
    public Result addDataSource(@RequestBody DataSourceInfo dataSourceInfo) throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        String url = String.format(DatabaseConnectUrl.MYSQL_CONNECT_URL, dataSourceInfo.getHost(), dataSourceInfo.getPort(), dataSourceInfo.getDatabaseName());
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(dataSourceInfo.getUserName());
        druidDataSource.setPassword(dataSourceInfo.getPassword());
        DataSourceConfig.setParameters(druidDataSource);
        DynamicDataSource.dataSourcesMap.put(dataSourceInfo.getHost(), druidDataSource);
        if (DynamicDataSource.dataSourcesMap.size() == 1) {
            DynamicDataSource.setDataSource(dataSourceInfo.getHost());
        }
        return Result.success();
    }

    @PostMapping("/set")
    public Result setDataSource(@RequestParam String dataSource) {
        DynamicDataSource.setDataSource(dataSource);
        return Result.success();
    }
}
