package com.generator.util;

import cn.hutool.core.lang.Snowflake;

/**
 * @author Alex
 * @date 2022/9/30
 */
public class SnowflakeId extends Snowflake {
    private static SnowflakeId instance = null;

    private SnowflakeId() {
        super();
    }

    public static SnowflakeId getInstance() {
        if (instance == null) {
            return new SnowflakeId();
        }
        return instance;
    }
}
