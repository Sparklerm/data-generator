package com.generator.model.dto;

import lombok.Data;

/**
 * 数据库连接信息
 *
 * @author Sparkler
 * @createDate 2022/11/15
 */

@Data
public class DataSourceInfo {
    /**
     * 数据库类型
     */
    private String databaseType;
    /**
     * 数据库Host
     */
    private String host;
    /**
     * 数据库端口
     */
    private Integer port;
    /**
     * 连接数据库名
     */
    private String databaseName;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
}
