package com.generator.service.impl;

import cn.hutool.core.date.DateUtil;
import com.generator.model.common.DateFormatPattern;
import com.generator.service.DataGenerator;
import com.generator.tool.DataGenerators;
import org.springframework.stereotype.Service;

import static com.generator.model.common.MySQLDataType.*;

/**
 * MySQL 数据生成方法
 *
 * @author Sparkler
 * @createDate 2022/11/11
 */
@Service
public class MySQLDataGenerator implements DataGenerator {

    @Override
    public Object dataReturn(String dataType, Integer length) {
        Object data = null;
        switch (dataType) {
            case TINYINT:
            case SMALLINT:
            case MEDIUMINT:
            case INT:
            case INTEGER:
                data = DataGenerators.intDataGenerate(length);
                break;
            case BIGINT:
                data = DataGenerators.longDataGenerate(length);
                break;
            case FLOAT:
            case DOUBLE:
            case DECIMAL:
                data = DataGenerators.decimalDataGenerate(length);
                break;
            case CHAR:
                data = DataGenerators.charDataGenerate(null);
                break;
            case VARCHAR:
            case TINYTEXT:
            case TEXT:
            case MEDIUMTEXT:
            case LONGTEXT:
                data = DataGenerators.stringDataGenerate(length);
                break;
            case TINYBLOB:
            case MEDIUMBLOB:
            case BLOB:
            case LONGBLOB:
                data = DataGenerators.byteDataGenerate(length);
                break;
            case DATE:
            case DATETIME:
                data = DataGenerators.dateDataGenerate();
                break;
            case TIME:
                data = DataGenerators.timeDataGenerate();
                break;
            case YEAR:
                data = DateUtil.format(DataGenerators.dateDataGenerate(), DateFormatPattern.YEAR);
                break;
            default:
                break;
        }
        return data;
    }

    @Override
    public Object pkReturn(String dataType) {
        Object data = null;
        switch (dataType) {
            case INT:
            case BIGINT:
                data = DataGenerators.idNumberGenerator();
                break;
            case VARCHAR:
                data = DataGenerators.idStrGenerator();
                break;
            default:
                break;
        }
        return data;
    }
}
