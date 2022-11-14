package com.generator.model.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sparkler
 * @createDate 2022/11/11
 */
@Getter
public enum DbDataTypeMatchEnum {

    /**
     * int
     */
    INT("int", "Integer"),
    /**
     * varchar
     */
    VARCHAR("varchar", "String"),
    /**
     * char
     */
    CHAR("char", "Char"),
    /**
     * date
     */
    DATE("date", "Date"),
    /**
     * datetime
     */
    DATETIME("datetime", "Date"),
    /**
     * bigint
     */
    BIGINT("bigint", "Long"),
    /**
     * decimal
     */
    DECIMAL("decimal", "BigDecimal");
    private static Map<String, DbDataTypeMatchEnum> enums = null;

    static {
        DbDataTypeMatchEnum[] enumConstants = DbDataTypeMatchEnum.class.getEnumConstants();
        enums = new HashMap<>(enumConstants.length);
        for (int i = 0; i < enumConstants.length; i++) {
            enums.put(enumConstants[i].getDbType(), enumConstants[i]);
        }
    }

    private final String dbType;
    private final String javaType;

    DbDataTypeMatchEnum(String dbType, String javaType) {
        this.dbType = dbType;
        this.javaType = javaType;
    }

    public static DbDataTypeMatchEnum getDataTypeByDbType(String dbType) {
        return enums.get(dbType);
    }

}
