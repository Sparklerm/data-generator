package com.sparkler.generator.model.vo;

import com.sparkler.generator.model.dto.ColumnsDO;
import com.sparkler.generator.model.dto.TablesDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
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
