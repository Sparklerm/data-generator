package com.sparkler.generator.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Sparkler
 * @createDate 2022/11/9
 */
@Data
public class TableColumnVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 表名
     */
    private String tableName;
    /**
     * 列名
     */
    private String columnName;
    /**
     * 序号
     */
    private Integer ordinalPosition;
    /**
     * 列默认值
     */
    private String columnDefault;
    /**
     * 是否为空
     */
    private String isNullable;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 字符串类型最大长度
     */
    private Long characterMaximumLength;
    /**
     * 整形类型长度
     */
    private Long numericPrecision;
    /**
     * 精度
     */
    private Long numericScale;

    /**
     * 列类型
     */
    private String columnType;
    /**
     * 剑类型
     */
    private Object columnKey;
    /**
     * 列策略
     */
    private String extra;

    /**
     * 类备注
     */
    private String columnComment;
}
