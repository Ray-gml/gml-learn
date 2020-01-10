package com.gml.multedatasourcejtaatomikos.config;

import lombok.Data;

/**
 * @description:
 * @author: gml
 * @create: 2020-01-10 14:20
 */

@Data
public class DbConfig {

    private String url;
    private String username;
    private String password;
    private int minPoolSize;
    private int maxPoolSize;
    private int maxLifetime;
    private int borrowConnectionTimeout;
    private int loginTimeout;
    private int maintenanceInterval;
    private int maxIdleTime;
    private String testQuery;

}
