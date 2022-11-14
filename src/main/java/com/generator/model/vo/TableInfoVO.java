package com.generator.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Sparkler
 * @createDate 2022/11/1
 */
@Data
public class TableInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String tableName;
    /**
     *
     */
    private String tableCollation;
    /**
     *
     */
    private String tableComment;

    private List<TableColumnVO> columns;
}
