package com.gml.excelpoi.annotation;

import org.apache.poi.hssf.util.HSSFColor;

import java.lang.annotation.*;

/**
 * @description: sheet页
 * @author: gml
 * @create: 2020-01-14 16:41
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExcelSheet {

    /**
     * 表名称
     * @return
     */
    String name() default "";

    /**
     * 表头颜色
     * @return
     */
    HSSFColor.HSSFColorPredefined headColor() default HSSFColor.HSSFColorPredefined.LIGHT_BLUE;

}
