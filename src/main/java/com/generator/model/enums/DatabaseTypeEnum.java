package com.generator.model.enums;

import com.generator.service.impl.MySQLDataGenerator;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sparkler
 * @createDate 2022/11/15
 */
@Getter
public enum DatabaseTypeEnum {
    /**
     * MySQL 数据生成器
     */
    MYSQL("MYSQL", MySQLDataGenerator.class);
    private static Map<String, DatabaseTypeEnum> dbTypes = null;

    static {
        DatabaseTypeEnum[] enumConstants = DatabaseTypeEnum.class.getEnumConstants();
        dbTypes = new HashMap<>(enumConstants.length);
        for (int i = 0; i < enumConstants.length; i++) {
            dbTypes.put(enumConstants[i].getDatabaseName(), enumConstants[i]);
        }
    }

    private final String DatabaseName;
    private final Class DatabaseGenerator;

    DatabaseTypeEnum(String databaseName, Class databaseGenerator) {
        DatabaseName = databaseName;
        DatabaseGenerator = databaseGenerator;
    }

    public static Class<?> getDataTypeByDbType(String dbType) {
        return dbTypes.get(dbType).getDatabaseGenerator();
    }
}
