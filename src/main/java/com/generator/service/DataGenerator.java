package com.generator.service;

/**
 * @author Sparkler
 * @createDate 2022/11/11
 */
public interface DataGenerator {

    /**
     * 生成随机数据
     *
     * @param dataType
     * @param number
     * @return
     */
    Object dataReturn(String dataType, Integer number);

    /**
     * 生成主键
     *
     * @param dataType
     * @return
     */
    Object pkReturn(String dataType);
}
