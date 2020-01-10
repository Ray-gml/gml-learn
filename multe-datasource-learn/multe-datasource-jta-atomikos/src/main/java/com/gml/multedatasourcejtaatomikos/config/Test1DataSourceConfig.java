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
@MapperScan(basePackages = {"com.gml.multedatasourcejtaatomikos.test1"}, sqlSessionTemplateRef = "test1SqlSessionTemplate")
public class Test1DataSourceConfig {

    @Bean(name = "test1DbConfig")
    @ConfigurationProperties(prefix = "spring.datasource.test1")
    public DbConfig test1DbConfig() {
        return new DbConfig();
    }


    // 配置数据源
    @Bean(name = "test1DataSource")
    public DataSource test1DataSource(@Qualifier("test1DbConfig")DbConfig test1DbConfig) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(test1DbConfig.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(test1DbConfig.getPassword());
        mysqlXaDataSource.setUser(test1DbConfig.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean test1DataSource = new AtomikosDataSourceBean();
        test1DataSource.setXaDataSource(mysqlXaDataSource);
        test1DataSource.setUniqueResourceName("test1DataSource");

        test1DataSource.setMinPoolSize(test1DbConfig.getMinPoolSize());
        test1DataSource.setMaxPoolSize(test1DbConfig.getMaxPoolSize());
        test1DataSource.setMaxLifetime(test1DbConfig.getMaxLifetime());
        test1DataSource.setBorrowConnectionTimeout(test1DbConfig.getBorrowConnectionTimeout());
        test1DataSource.setLoginTimeout(test1DbConfig.getLoginTimeout());
        test1DataSource.setMaintenanceInterval(test1DbConfig.getMaintenanceInterval());
        test1DataSource.setMaxIdleTime(test1DbConfig.getMaxIdleTime());
        test1DataSource.setTestQuery(test1DbConfig.getTestQuery());
        return test1DataSource;
    }

    @Bean(name = "test1SqlSessionFactory")
    public SqlSessionFactory test1SqlSessionFactory(@Qualifier("test1DataSource") DataSource test1DataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(test1DataSource);
        //设置xml文件位置
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:test1/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "test1SqlSessionTemplate")
    public SqlSessionTemplate test1SqlSessionTemplate(@Qualifier("test1SqlSessionFactory") SqlSessionFactory test1SqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(test1SqlSessionFactory);
    }
}
