package com.generator.model.enums;

import lombok.Getter;

/**
 * 主键自增
 *
 * @author Sparkler
 * @createDate 2022/11/12
 */
@Getter
public enum ExtraEnum {

    /**
     * 无
     */
    NONE(""),
    /**
     * 自增
     */
    AUTO_INCREMENT("auto_increment");

    private final String code;

    ExtraEnum(String code) {
        this.code = code;
    }
}
