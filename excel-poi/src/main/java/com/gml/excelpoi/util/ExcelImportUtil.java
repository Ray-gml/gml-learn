package com.gml.excelpoi.util;

import com.gml.excelpoi.annotation.ExcelSheet;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @description: Excel读取
 * @author: gml
 * @create: 2020-01-14 16:48
 */
@Slf4j
public class ExcelImportUtil {

    /**
     * 根据文件和密码解析表格
     * @param file
     * @param password
     * @param shellClass
     * @return
     */
    public static List<T> parseExcel(File file, String password, Class<T> shellClass){
        try {
            Workbook workbook;
            if (Objects.isNull(password)){
                workbook = WorkbookFactory.create(file);
            }else {
                workbook = WorkbookFactory.create(file, password);
            }
            List<T> dataList = parseSheet(workbook, shellClass);
            return dataList;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据文件路径和密码解析表格
     * @param filePath
     * @param password
     * @param shellClass
     * @return
     */
    public static List<T> parseExcel(String filePath, String password, Class<T> shellClass){
        File file = new File(filePath);
        List<T> dataList = parseExcel(file, password, shellClass);
        return dataList;
    }

    /**
     * 根据流
     * @param inputStream
     * @param sheetClass
     * @return
     */
    public static List<T> parseExcel(InputStream inputStream, Class<T> sheetClass){
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            List<T> dataList = parseSheet(workbook, sheetClass);
            return dataList;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private static List<T> parseSheet(Workbook workbook, Class<T> shellClass) {
        //获取类的注解
        ExcelSheet excelSheet = shellClass.getAnnotation(ExcelSheet.class);
        //如果有注解，并且注解有定义shell的名称，则使用这个名称，否则使用类名的简写
        String shellName = (excelSheet != null && excelSheet.name() != null && !Objects.isNull(excelSheet.name().trim())) ? excelSheet.name() : shellClass.getSimpleName();
        //存放类的非静态变量
        List<Field> fieldList = new ArrayList<>();
        //将静态成员变量过滤掉
        for (Field field : shellClass.getDeclaredFields()){
            if (Modifier.isStatic(field.getModifiers())){
                continue;
            }
            fieldList.add(field);
        }
        if (fieldList == null || fieldList.size() == 0){
            log.error("error class 【{}}】 dont have filed", shellClass.getName());
            throw new RuntimeException("error class 【" + shellClass.getName() +"】 dont have filed");
        }

        Sheet sheet = workbook.getSheet(shellName);
        if (sheet == null){
            log.error("未找到名为【{}】的sheet页", shellName);
            return null;
        }

        Row row = sheet.getRow(0);

        return null;
    }
}
