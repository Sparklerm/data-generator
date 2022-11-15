package com.generator.model.common;

/**
 * 数据库连接Url
 *
 * @author Sparkler
 * @createDate 2022/11/15
 */
public class DatabaseConnectUrl {
    /**
     * MySQL 连接Url
     */
    public static final String MYSQL_CONNECT_URL = "jdbc:mysql://%s:%s/%s?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC&useAffectedRows=true";
}
