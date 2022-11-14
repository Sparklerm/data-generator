package com.generator.service;

import com.generator.model.enums.DbDataTypeMatchEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Sparkler
 * @createDate 2022/11/11
 */
public interface DataGenerator {

    /**
     * Id 生成（雪花算法）
     *
     * @return
     */
    long idNumberGenerator();

    /**
     * Id 生成（雪花算法）
     *
     * @return
     */
    String idStrGenerator();

    /**
     * int 数据生成
     *
     * @param length 数据长度
     * @return int 数据值
     */
    int intDataGenerate(Integer length);

    /**
     * long 数据生成
     *
     * @param length 数据长度
     * @return long 数据值
     */
    long longDataGenerate(Integer length);

    /**
     * char 数据生成
     *
     * @param baseString 可选数据
     * @return long 数据值
     */
    char charDataGenerate(String baseString);

    /**
     * String 数据生成
     *
     * @param length 数据长度
     * @return long 数据值
     */
    String stringDataGenerate(Integer length);

    /**
     * Decimal 数据生成
     *
     * @param number 小数位
     * @return
     */
    BigDecimal decimalDataGenerate(Integer number);

    /**
     * Date 数据生成
     *
     * @return date 数据值
     */
    Date dateDataGenerate();

    Object dataReturn(DbDataTypeMatchEnum dataType, Integer number);

    Object pkReturn(DbDataTypeMatchEnum dataType);
}
