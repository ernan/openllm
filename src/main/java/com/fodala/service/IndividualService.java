package com.fodala.service;

import com.fodala.pojo.Individual;

import java.util.List;

public interface IndividualService {

    List<Individual> all();

    Individual createEmpty();

    void insert(Individual individual);

    void update(Individual individual);

    void delete(Individual individual);

    Individual findById(Integer id);
}
