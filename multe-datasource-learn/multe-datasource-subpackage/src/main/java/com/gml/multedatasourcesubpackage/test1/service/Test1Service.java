package com.gml.multedatasourcesubpackage.test1.service;

import com.gml.multedatasourcesubpackage.test1.mapper.Test1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: gml
 * @create: 2020-01-09 17:10
 */
@Service
public class Test1Service {

    @Autowired
    private Test1Mapper test1Mapper;

    public Integer test1Add(String name, Integer cost) {
        int result = test1Mapper.insertBook(name, cost);

        return result;
    }
}
