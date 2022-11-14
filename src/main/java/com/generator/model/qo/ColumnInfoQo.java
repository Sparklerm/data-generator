package com.generator.model.qo;

import lombok.Data;

/**
 * @author Sparkler
 * @createDate 2022/11/12
 */

@Data
public class ColumnInfoQo {
    /**
     * 列名
     */
    private String columnName;

    /**
     * 是否为主键
     */
    private Integer primaryKey;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 数据长度
     */
    private Integer dataLength;
    /**
     *
     */
    private String extra;
}
