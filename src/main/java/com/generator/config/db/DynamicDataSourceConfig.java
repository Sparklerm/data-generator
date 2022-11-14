//package com.sparkler.generator.config.db;
//
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author Sparkler
// * @createDate 2022/11/13
// */
//@Configuration
//public class DynamicDataSourceConfig {
//    /**
//     * 动态数据源
//     */
//    @Bean
//    public DynamicDataSource dynamicDataSource() {
//        DynamicDataSource dataSource = new DynamicDataSource();
//        Map<Object, Object> targetDataSources = new HashMap<>();
//        dataSource.setTargetDataSources(targetDataSources);
//        return dataSource;
//    }
//
//    /**
//     * 会话工厂
//     */
//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
//        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//        configuration.setMapUnderscoreToCamelCase(true);
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
//        sqlSessionFactoryBean.setConfiguration(configuration);
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:/repository/*.xml"));
//        return sqlSessionFactoryBean;
//    }
//
//    /**
//     * 事务管理器
//     */
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(dynamicDataSource());
//    }
//}
