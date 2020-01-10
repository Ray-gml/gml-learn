package com.gml.multedatasourcejtaatomikos.test1.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: gml
 * @create: 2020-01-09 17:10
 */
@Repository
public interface Test1Mapper {

    int insertBook(@Param("name") String name, @Param("cost") Integer cost);
}
