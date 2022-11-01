package com.sparkler.generator.model.vo;

import com.sparkler.generator.model.dto.ColumnsDO;
import com.sparkler.generator.model.dto.TablesDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Sparkler
 * @createDate 2022/11/1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TableInfoVO extends TablesDO {
    private List<ColumnsDO> columns;
}
