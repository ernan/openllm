package com.fodala.mapper;

import com.fodala.pojo.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CityMapper {

    @Select("select id, name, state, country from city where id = #{id}")
    City findById(@Param("id") Long id);

    @Select("select id, name, state, country from city where state = #{state}")
    City findByState(@Param("state") String state);

    @Select("select id, name, state, country from city where name = #{name}")
    City findByName(@Param("name") String name);

}