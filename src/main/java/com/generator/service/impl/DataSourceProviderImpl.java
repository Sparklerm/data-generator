//package com.sparkler.generator.service.impl;
//
//import com.sparkler.generator.config.db.DynamicDataSourceService;
//import com.sparkler.generator.service.DataSourceProvider;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.sql.DataSource;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
///**
// * @author Sparkler
// * @createDate 2022/11/13
// */
//
//@Component
//@ConfigurationProperties(prefix = "spring.datasource.druid")
//public class DataSourceProviderImpl implements DataSourceProvider {
//
//    private List<Map<String, DataSourceProperties>> dataSources;
//
//    private DataSource buildDataSource(DataSourceProperties prop) {
//        DataSourceBuilder<?> builder = DataSourceBuilder.create();
//        builder.driverClassName(prop.getDriverClassName());
//        builder.username(prop.getUsername());
//        builder.password(prop.getPassword());
//        builder.url(prop.getUrl());
//        return builder.build();
//    }
//
//    @Override
//    public List<DataSource> provide() {
//        List<DataSource> res = new ArrayList<>();
//        dataSources.forEach(map -> {
//            Set<String> keys = map.keySet();
//            keys.forEach(key -> {
//                DataSourceProperties properties = map.get(key);
//                DataSource dataSource = buildDataSource(properties);
//                DynamicDataSourceService.addDataSource(key, dataSource);
//            });
//        });
//        return res;
//    }
//
//    @PostConstruct
//    public void init() {
//        provide();
//    }
//
//    public List<Map<String, DataSourceProperties>> getDataSources() {
//        return dataSources;
//    }
//
//    public void setDataSources(List<Map<String, DataSourceProperties>> dataSources) {
//        this.dataSources = dataSources;
//    }
//}
