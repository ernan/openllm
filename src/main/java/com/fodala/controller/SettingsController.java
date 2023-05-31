package com.fodala.controller;

import com.fodala.pojo.Setting;
import com.fodala.service.SettingsService;
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
public class SettingsController {
    private static final Logger logger = LoggerFactory.getLogger(SettingsController.class);

    @Autowired
    private SettingsService settingsService;

    @RequestMapping(value = "/setting", method = RequestMethod.GET)
    public String Setting(@RequestParam(value = "id", required = false) Integer id, Model model) {
        Setting setting;
        if (id != null) {
            setting = settingsService.findById(id);
            model.addAttribute("history", settingsService.history(id));
        } else {
            setting = settingsService.createEmpty();
        }
        model.addAttribute("setting", setting);
        return "setting/edit";
    }

    @RequestMapping(value = "/setting/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam(value = "id", required = false) Integer id) {
        if (id != null) {
            settingsService.delete(id);
        }
        return new ModelAndView("redirect:/setting");
    }

    @RequestMapping(value = "/setting", method = RequestMethod.POST)
    public ModelAndView SettingSave(Setting Setting, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (Setting.getId() == null) {
                settingsService.insert(Setting);
            } else {
                if (settingsService.findById(Setting.getId()) != null) {
                    settingsService.update(Setting);
                }
            }
        }
        ModelAndView modelAndView = new ModelAndView("setting/edit");
        modelAndView.addObject("setting", Setting);
        modelAndView.addObject("history", settingsService.history(Setting.getId()));
        return modelAndView;
    }


    @RequestMapping("/settings")
    public String Settings(Model model) {
        logger.info("Getting all Settings");
        List<Setting> settingList = settingsService.all();
        logger.info("Found {} Settings", settingList.size());
        model.addAttribute("settings", settingList);
        model.addAttribute("currentTab", "setting");
        return "setting/list";
    }
}
