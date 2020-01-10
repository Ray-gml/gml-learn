package com.gml.multedatasourcejtaatomikos.config;

import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @description:
 * @author: gml
 * @create: 2020-01-09 17:01
 */

@Component
@MapperScan(basePackages = {"com.gml.multedatasourcejtaatomikos.test2"}, sqlSessionTemplateRef = "test2SqlSessionTemplate")
public class Test2DataSourceConfig {

    @Bean(name = "test2DbConfig")
    @ConfigurationProperties(prefix = "spring.datasource.test2")
    public DbConfig test2DbConfig() {
        return new DbConfig();
    }


    // 配置数据源
    @Bean(name = "test2DataSource")
    public DataSource test2DataSource(@Qualifier("test2DbConfig")DbConfig test2DbConfig) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(test2DbConfig.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(test2DbConfig.getPassword());
        mysqlXaDataSource.setUser(test2DbConfig.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean test2DataSource = new AtomikosDataSourceBean();
        test2DataSource.setXaDataSource(mysqlXaDataSource);
        test2DataSource.setUniqueResourceName("test2DataSource");

        test2DataSource.setMinPoolSize(test2DbConfig.getMinPoolSize());
        test2DataSource.setMaxPoolSize(test2DbConfig.getMaxPoolSize());
        test2DataSource.setMaxLifetime(test2DbConfig.getMaxLifetime());
        test2DataSource.setBorrowConnectionTimeout(test2DbConfig.getBorrowConnectionTimeout());
        test2DataSource.setLoginTimeout(test2DbConfig.getLoginTimeout());
        test2DataSource.setMaintenanceInterval(test2DbConfig.getMaintenanceInterval());
        test2DataSource.setMaxIdleTime(test2DbConfig.getMaxIdleTime());
        test2DataSource.setTestQuery(test2DbConfig.getTestQuery());
        return test2DataSource;
    }

    @Bean(name = "test2SqlSessionFactory")
    public SqlSessionFactory test2SqlSessionFactory(@Qualifier("test2DataSource") DataSource test2DataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(test2DataSource);
        //设置xml文件位置
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:test2/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "test2SqlSessionTemplate")
    public SqlSessionTemplate test2SqlSessionTemplate(@Qualifier("test2SqlSessionFactory") SqlSessionFactory test2SqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(test2SqlSessionFactory);
    }
}
