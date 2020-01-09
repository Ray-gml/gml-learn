package com.gml.multedatasourcesubpackage.test2.service;

import com.gml.multedatasourcesubpackage.test2.mapper.Test2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: gml
 * @create: 2020-01-09 17:10
 */
@Service
public class Test2Service {
    @Autowired
    private Test2Mapper test2Mapper;

    public Integer test2Add(String name, Integer cost) {
        int result = test2Mapper.insertBook(name, cost);

        return result;
    }
}
