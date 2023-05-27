package com.fodala.controller;

import com.fodala.pojo.Container;
import com.fodala.service.ContainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ContainerController {
    private static final Logger logger = LoggerFactory.getLogger(ContainerController.class);

    @Autowired
    private ContainerService containerService;

    @RequestMapping(value = "/container", method = RequestMethod.GET)
    public String container(@RequestParam(value = "id", required = false) Integer id, Model model) {
        Container container;
        if (id != null) {
            container = containerService.findById(id);
            model.addAttribute("history", containerService.history(id));
        } else {
            container = containerService.createEmpty();
        }
        model.addAttribute("container", container);
        return "container/edit";
    }

    @RequestMapping(value = "/container/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam(value = "id", required = false) String id, Model model) {
        if (id != null) {
            containerService.delete(Integer.valueOf(id));
        }
        return new ModelAndView("redirect:/containers");
    }

    @RequestMapping(value = "/container", method = RequestMethod.POST)
    public ModelAndView containerSave(Container container, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (container.getId() == null) {
                containerService.insert(container);
            } else {
                if (containerService.findById(container.getId()) != null) {
                    containerService.update(container);
                }
            }
        }
        ModelAndView containerModel = new ModelAndView("container/edit");
        containerModel.addObject("container", container);
        containerModel.addObject("history", containerService.history(container.getId()));
        return containerModel;
    }


    @RequestMapping("/containers")
    public String containers(Model model) {
        logger.info("Getting all containers");
        List<Container> containerList = containerService.all();
        logger.info("Found {} containers", containerList.size());
        model.addAttribute("containers", containerList);
        model.addAttribute("currentTab", "containers");
        return "container/list";
    }
}
