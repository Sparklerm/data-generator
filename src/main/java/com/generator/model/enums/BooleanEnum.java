package com.generator.model.enums;

import lombok.Getter;

/**
 * @author Sparkler
 * @createDate 2022/11/12
 */
@Getter
public enum BooleanEnum {

    YES(0), NO(1);

    private final int code;

    BooleanEnum(int code) {
        this.code = code;
    }
}
