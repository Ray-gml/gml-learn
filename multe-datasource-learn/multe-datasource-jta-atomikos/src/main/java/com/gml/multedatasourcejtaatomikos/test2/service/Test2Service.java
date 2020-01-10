package com.gml.multedatasourcejtaatomikos.test2.service;

import com.gml.multedatasourcejtaatomikos.test1.mapper.Test1Mapper;
import com.gml.multedatasourcejtaatomikos.test2.mapper.Test2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: gml
 * @create: 2020-01-09 17:10
 */
@Service
public class Test2Service {
    @Autowired
    private Test2Mapper test2Mapper;
    @Autowired
    private Test1Mapper test1Mapper;

    public Integer test2Add(String name, Integer cost) {
        int result = test2Mapper.insertBook(name, cost);

        return result;
    }


    @Transactional
    public Integer test2AndTest2Add(String name, Integer cost) {

        int result1 = test1Mapper.insertBook(name, cost);
        int num = cost / 0;

        int result2 = test2Mapper.insertBook(name, cost);


        return result1 + result2;
    }
}
