package com.generator.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.generator.model.common.ConfigKey;
import com.generator.model.enums.DbDataTypeMatchEnum;
import com.generator.service.DataGenerator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Sparkler
 * @createDate 2022/11/11
 */
@Service
public class DataGeneratorImpl implements DataGenerator {

    /**
     * 雪花算法Id生成
     */
    private static final Snowflake snowflake = IdUtil.getSnowflake();

    @Override
    public long idNumberGenerator() {
        return snowflake.nextId();
    }

    @Override
    public String idStrGenerator() {
        return snowflake.nextIdStr();
    }

    @Override
    public int intDataGenerate(Integer length) {
        return RandomUtil.randomInt(ConfigKey.RANDOM_INT_MIN, ConfigKey.RANDOM_INT_MAX);
    }

    @Override
    public long longDataGenerate(Integer length) {
        return RandomUtil.randomLong(ConfigKey.RANDOM_LONG_MIN, ConfigKey.RANDOM_LONG_MAX);
    }

    @Override
    public char charDataGenerate(String baseString) {
        if (StrUtil.isNotBlank(baseString)) {
            return RandomUtil.randomChar(baseString);
        }
        return RandomUtil.randomChar();
    }

    @Override
    public String stringDataGenerate(Integer length) {
        return RandomUtil.randomString(length);
    }

    @Override
    public BigDecimal decimalDataGenerate(Integer number) {
        if (ObjectUtil.isNull(number)) {
            number = ConfigKey.DEFAULT_DECIMAL_SCALE;
        }

        return BigDecimal.valueOf(RandomUtil.randomDouble(ConfigKey.RANDOM_INT_MIN, ConfigKey.RANDOM_INT_MAX)).setScale(number, ConfigKey.DECIMAL_ROUNDING_MODE);
    }

    @Override
    public Date dateDataGenerate() {
        return RandomUtil.randomDay(ConfigKey.RANDOM_DATE_MIN, ConfigKey.RANDOM_DATE_MAX);
    }

    @Override
    public Object dataReturn(DbDataTypeMatchEnum dataType, Integer number) {
        Object data = null;
        switch (dataType) {
            case INT:
                data = this.intDataGenerate(number);
                break;
            case BIGINT:
                data = this.longDataGenerate(number);
                break;
            case CHAR:
                data = this.charDataGenerate(null);
                break;
            case VARCHAR:
                data = this.stringDataGenerate(number);
                break;
            case DATE:
            case DATETIME:
                data = this.dateDataGenerate();
                break;
            case DECIMAL:
                data = this.decimalDataGenerate(number);
                break;
            default:
                break;
        }
        return data;
    }

    @Override
    public Object pkReturn(DbDataTypeMatchEnum dataType) {
        Object data = null;
        switch (dataType) {
            case INT:
            case BIGINT:
                data = this.idNumberGenerator();
                break;
            case VARCHAR:
                data = this.idStrGenerator();
                break;
            default:
                break;
        }
        return data;
    }
}
