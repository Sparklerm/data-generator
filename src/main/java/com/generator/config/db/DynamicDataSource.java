//package com.sparkler.generator.config.db;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//
///**
// * @author Sparkler
// * @createDate 2022/11/13
// */
//public class DynamicDataSource extends AbstractRoutingDataSource {
//    @Value("${spring.datasource.default-db-key}")
//    private String defaultDbKey;
//
//    @Override
//    protected Object determineCurrentLookupKey() {
//        String currentDb = DynamicDataSourceService.currentDb();
//        if (currentDb == null) {
//            return defaultDbKey;
//        }
//        return currentDb;
//    }
//}
