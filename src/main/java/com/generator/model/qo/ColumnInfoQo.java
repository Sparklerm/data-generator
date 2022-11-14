package com.generator.model.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Sparkler
 * @createDate 2022/11/12
 */

@Data
@ApiModel(value = "ColumnInfoQo", description = "表结构-列信息")
public class ColumnInfoQo {
    /**
     * 列名
     */
    @ApiModelProperty("列名")
    private String columnName;

    /**
     * 是否为主键 0，1
     */
    @ApiModelProperty("是否为主键")
    private Integer primaryKey;

    /**
     * 数据类型
     */
    @ApiModelProperty("数据类型")
    private String dataType;

    /**
     * 数据长度
     */
    @ApiModelProperty("数据长度")
    private Integer dataLength;
    /**
     * 键策略
     */
    @ApiModelProperty("键策略")
    private String extra;
}
