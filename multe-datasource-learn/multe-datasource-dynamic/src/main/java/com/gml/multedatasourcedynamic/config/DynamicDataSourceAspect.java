package com.gml.multedatasourcedynamic.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @description:
 * @author: gml
 * @create: 2020-01-13 10:16
 */
@Slf4j
@Aspect
@Component
@Order(-1)
public class DynamicDataSourceAspect {

    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, TargetDataSource ds){
        String dsName = ds.name();
        if (!DynamicDataSourceContextHolder.containsDataSource(dsName)){
            log.error("数据源【{}】不存在，使用默认数据源【{}】", dsName, point.getSignature());
        }else {
            log.info("use DataSource:【{}】 > 【{}】",  dsName, point.getSignature());
            DynamicDataSourceContextHolder.setDataSourceType(dsName);
        }
    }

    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, TargetDataSource ds){
        log.info("Revert  DataSource: 【{}】 > 【{}】", ds.name(), point.getSignature());
        DynamicDataSourceContextHolder.clearDataSourceType();
    }

}
