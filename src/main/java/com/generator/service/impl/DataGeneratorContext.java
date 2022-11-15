package com.generator.service.impl;

import com.generator.config.exception.BizException;
import com.generator.model.enums.DatabaseTypeEnum;
import com.generator.service.DataGenerator;
import com.generator.tool.SpringUtils;

/**
 * @author Sparkler
 * @createDate 2022/11/15
 */
public class DataGeneratorContext {
    private DataGenerator dataGenerator;

    public DataGeneratorContext(String dbType) {
        Class<?> dataTypeByDbType = DatabaseTypeEnum.getDataTypeByDbType(dbType);
        if (DataGenerator.class.isAssignableFrom(dataTypeByDbType)) {
            this.dataGenerator = (DataGenerator) SpringUtils.getBean(dataTypeByDbType);
        } else {
            throw new BizException(dbType + " No data generator was implemented");
        }
    }

    /**
     * 生成随机数据
     *
     * @param dataType
     * @param number
     * @return
     */
    public Object dataReturn(String dataType, Integer number) {
        return dataGenerator.dataReturn(dataType, number);
    }

    /**
     * 生成主键
     *
     * @param dataType
     * @return
     */
    public Object pkReturn(String dataType) {
        return dataGenerator.pkReturn(dataType);
    }
}
