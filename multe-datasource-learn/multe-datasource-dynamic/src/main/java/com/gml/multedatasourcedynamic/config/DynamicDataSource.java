package com.gml.multedatasourcedynamic.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @description: 动态数据源
 * @author: gml
 * @create: 2020-01-13 10:18
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
