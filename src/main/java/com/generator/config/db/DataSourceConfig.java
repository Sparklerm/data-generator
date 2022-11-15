package com.generator.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author Sparkler
 * @createDate 2022/11/15
 */
@Configuration
public class DataSourceConfig {
    public static DruidDataSource setParameters(DruidDataSource druidDataSource) throws SQLException {
        //初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时
        druidDataSource.setInitialSize(1);
        //最大连接池数量
        druidDataSource.setMaxActive(20);
        //获取连接时最大等待时间，单位毫秒。当链接数已经达到了最大链接数的时候，应用如果还要获取链接就会出现等待的现象，等待链接释放并回到链接池，如果等待的时间过长就应该踢掉这个等待，不然应用很可能出现雪崩现象
        druidDataSource.setMaxWait(60000);
        //最小连接池数量
        druidDataSource.setMinIdle(5);
        String validationQuery = "select 1 from dual";
//            if("mysql".equalsIgnoreCase(databasetype)) {
//                driveClass = DBUtil.mysqldriver;
//                validationQuery = "select 1";
//            } else if("oracle".equalsIgnoreCase(databasetype)){
//                driveClass = DBUtil.oracledriver;
//                druidDataSource.setPoolPreparedStatements(true); //是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
//                druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(50);
//                int sqlQueryTimeout = ADIPropUtil.sqlQueryTimeOut();
//                druidDataSource.setConnectionProperties("oracle.net.CONNECT_TIMEOUT=6000;oracle.jdbc.ReadTimeout="+sqlQueryTimeout);//对于耗时长的查询sql，会受限于ReadTimeout的控制，单位毫秒
//            } else if("sqlserver2000".equalsIgnoreCase(databasetype)){
//                driveClass = DBUtil.sql2000driver;
//                validationQuery = "select 1";
//            } else if("sqlserver".equalsIgnoreCase(databasetype)){
//                driveClass = DBUtil.sql2005driver;
//                validationQuery = "select 1";
//            }
        //申请连接时执行validationQuery检测连接是否有效，这里建议配置为TRUE，防止取到的连接不可用
        druidDataSource.setTestOnBorrow(true);
        //建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
        druidDataSource.setTestWhileIdle(true);
        //用来检测连接是否有效的sql，要求是一个查询语句。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。
        druidDataSource.setValidationQuery(validationQuery);
        //属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：监控统计用的filter:stat日志用的filter:log4j防御sql注入的filter:wall
        druidDataSource.setFilters("stat");
        //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        //配置一个连接在池中最小生存的时间，单位是毫秒，这里配置为3分钟180000
        druidDataSource.setMinEvictableIdleTimeMillis(180000);
        //打开druid.keepAlive之后，当连接池空闲时，池中的minIdle数量以内的连接，空闲时间超过minEvictableIdleTimeMillis，则会执行keepAlive操作，即执行druid.validationQuery指定的查询SQL，一般为select * from dual，只要minEvictableIdleTimeMillis设置的小于防火墙切断连接时间，就可以保证当连接空闲时自动做保活检测，不会被防火墙切断
        druidDataSource.setKeepAlive(true);
        //是否移除泄露的连接/超过时间限制是否回收。
        druidDataSource.setRemoveAbandoned(false);
        //泄露连接的定义时间(要超过最大事务的处理时间)；单位为秒。这里配置为1小时
        druidDataSource.setRemoveAbandonedTimeout(3600);
        // 移除泄露连接发生是是否记录日志
        druidDataSource.setLogAbandoned(true);
        druidDataSource.init();
        return druidDataSource;
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource defaultDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    @DependsOn({"springUtils", "defaultDataSource"})
    public DynamicDataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(DynamicDataSource.dataSourcesMap);
        return dynamicDataSource;
    }
}
