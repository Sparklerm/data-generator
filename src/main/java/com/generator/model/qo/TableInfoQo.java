package com.generator.model.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Sparkler
 * @createDate 2022/11/12
 */
@Data
@ApiModel(value = "TableInfoQo", description = "需要插入数据的表结构信息")
public class TableInfoQo {
    /**
     * 表名
     */
    @ApiModelProperty(value = "表名")
    private String tableName;

    /**
     * 数据量
     */
    @ApiModelProperty(value = "需要插入的数据量")
    private Integer records;
    /**
     * 列
     */
    @ApiModelProperty(value = "列信息")
    private List<ColumnInfoQo> columns;
}
