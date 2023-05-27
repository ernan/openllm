package com.fodala.controller;


import com.fodala.pojo.Individual;
import com.fodala.service.IndividualService;
import com.fodala.web.api.IndividualValdator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndividualController {
    private static final Logger logger = LoggerFactory.getLogger(IndividualController.class);

    @Autowired
    private IndividualService individualService;

    @InitBinder("individual")
    protected void initIndividualValdator(WebDataBinder binder) {
        binder.addValidators(new IndividualValdator());
    }

    @RequestMapping(value = "/individual", method = RequestMethod.GET)
    public String individual(@RequestParam(value = "id", required = false) String id, Model model) {
        if (id != null) {
            Individual individual = individualService.findById(Integer.valueOf(id));
            model.addAttribute("individual", individual);
        } else {
            Individual individual = individualService.createEmpty();
            model.addAttribute("individual", individual);
        }
        return "individual";
    }

    @RequestMapping(value = "/individual", method = RequestMethod.POST)
    public String doSave(Individual individual, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            if (individual.getId() == null) {
                individualService.insert(individual);
            } else {
                if (individualService.findById(individual.getId()) != null) {
                    individualService.update(individual);
                }
            }
        }
        return "individual";
    }

    @RequestMapping("/list")
    public String individual(Model model) {
        logger.info("Getting all individuals");
        List<Individual> individualList = individualService.all();
        logger.info("Found {} individuals", individualList.size());
        model.addAttribute("individuals", individualList);
        return "list";
    }
}
