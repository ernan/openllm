package com.fodala.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CalendarController {
    private static final Logger logger = LoggerFactory.getLogger(CalendarController.class);

    @RequestMapping("/calendar")
    public String month(Model model) {
        logger.info("Getting calendar");
        return "calendar/month";
    }
}
