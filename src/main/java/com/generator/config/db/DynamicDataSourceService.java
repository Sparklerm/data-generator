//package com.sparkler.generator.config.db;
//
//import com.sparkler.generator.util.StaticMethodGetBean;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author Sparkler
// * @createDate 2022/11/13
// */
//public class DynamicDataSourceService {
//    private static final Logger log = LoggerFactory.getLogger(DynamicDataSourceService.class);
//
//    private static final Map<Object, Object> dataSources = new HashMap<>();
//    private static final ThreadLocal<String> dbKeys = ThreadLocal.withInitial(() -> null);
//
//    /**
//     * 动态添加一个数据源
//     *
//     * @param name       数据源的key
//     * @param dataSource 数据源对象
//     */
//    public static void addDataSource(String name, DataSource dataSource) {
//        DynamicDataSource dynamicDataSource = StaticMethodGetBean.getBean(DynamicDataSource.class);
//        dataSources.put(name, dataSource);
//        dynamicDataSource.setTargetDataSources(dataSources);
//        dynamicDataSource.afterPropertiesSet();
//
//        log.info("添加了数据源:{}", name);
//    }
//
//    /**
//     * 切换数据源
//     */
//    public static void switchDb(String dbKey) {
//        dbKeys.set(dbKey);
//    }
//
//    /**
//     * 重置数据源
//     */
//    public static void resetDb() {
//        dbKeys.remove();
//    }
//
//    /**
//     * 获取当前数据源
//     */
//    public static String currentDb() {
//        return dbKeys.get();
//    }
//}
