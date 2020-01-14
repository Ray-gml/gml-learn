package com.gml.multedatasourcedynamic.config;

import java.lang.annotation.*;

/**
 * 在方法上使用，用于指定使用哪个数据源
 * @author gml
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String name();
}
