package com.fodala.service.impl;

import com.fodala.mapper.IndividualMapper;
import com.fodala.pojo.Individual;
import com.fodala.service.IndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
public class IndividualServiceImpl implements IndividualService {

    @Autowired
    private IndividualMapper individualRepository;

    @Override
    public List<Individual> all() {
        List<Individual> result = individualRepository.all();

        result.sort(Comparator.comparing(Individual::getId));
        return result;
    }

    @Override
    public Individual findById(Integer id) {
        return individualRepository.findById(id).orElse(null);
    }

    @Override
    public void insert(Individual individual) {
        individualRepository.insert(individual);
    }

    @Override
    public void update(Individual individual) {
        individualRepository.update(individual);
    }

    @Override
    public void delete(Individual individual) {
        individualRepository.delete(individual.getId());
    }

    @Override
    public Individual createEmpty() {
        Random r = new Random();
        Individual individual = new Individual();
        individual.setName("A Name" + r.nextInt(10000));
        individual.setSsn(r.nextInt(10000) + "GH");
        individual.setDateOfBirth("2000-01-01");
        individual.setPhone("087-555-5555");
        return individual;
    }

}
