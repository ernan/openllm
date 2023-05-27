package com.fodala.mapper;

import com.fodala.pojo.Individual;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IndividualMapper {

    List<Individual> all();

    void insert(@Param("individual") Individual individual);

    void update(@Param("individual") Individual individual);

    void delete(@Param("id") Integer id);

    //    @Results({
//            @Result(property = "dateOfBirth", column = "DATE_OF_BIRTH"),
//    })
    Optional<Individual> findById(Integer id);
}
