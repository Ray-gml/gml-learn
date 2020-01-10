package com.gml.multedatasourcejtaatomikos.controller;

import com.gml.multedatasourcejtaatomikos.test1.service.Test1Service;
import com.gml.multedatasourcejtaatomikos.test2.service.Test2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: gml
 * @create: 2020-01-09 17:08
 */
@RestController
public class TestController {

    @Autowired
    private Test1Service test1Service;

    @Autowired
    private Test2Service test2Service;

    @RequestMapping("/test1Add")
    public Integer test1Add(String name, Integer cost) {
        return test1Service.test1Add(name, cost);
    }

    @RequestMapping("/test2Add")
    public Integer test2Add(String name, Integer cost) {
        return test2Service.test2Add(name, cost);
    }

    @RequestMapping("/test2AndTest2Add")
    public Integer test2AndTest2Add(String name, Integer cost) {
        return test2Service.test2AndTest2Add(name, cost);
    }

}
