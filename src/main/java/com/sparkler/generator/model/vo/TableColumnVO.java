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
     *
     */
    private String tableName;
    /**
     *
     */
    private String columnName;
    /**
     *
     */
    private Integer ordinalPosition;
    /**
     *
     */
    private String columnDefault;
    /**
     *
     */
    private String isNullable;
    /**
     *
     */
    private String dataType;
    /**
     *
     */
    private Long characterMaximumLength;
    /**
     *
     */
    private Long characterOctetLength;
    /**
     *
     */
    private Long numericPrecision;
    /**
     *
     */
    private Long numericScale;
    /**
     *
     */
    private Integer datetimePrecision;
    /**
     *
     */
    private String characterSetName;
    /**
     *
     */
    private String collationName;
    /**
     *
     */
    private String columnType;
    /**
     *
     */
    private Object columnKey;
    /**
     *
     */
    private String extra;
    /**
     *
     */
    private String privileges;
    /**
     *
     */
    private String columnComment;
    /**
     *
     */
    private String generationExpression;
    /**
     *
     */
    private Integer srsId;
}
