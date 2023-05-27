package com.fodala.controller;

import com.fodala.pojo.Campaign;
import com.fodala.service.CampaignService;
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
public class CampaignController {
    private static final Logger logger = LoggerFactory.getLogger(CampaignController.class);

    @Autowired
    private CampaignService campaignService;

    @RequestMapping(value = "/campaign", method = RequestMethod.GET)
    public String campaign(@RequestParam(value = "id", required = false) Integer id, Model model) {
        Campaign campaign;
        if (id != null) {
            campaign = campaignService.findById(id);
            model.addAttribute("history", campaignService.history(id));
        } else {
            campaign = campaignService.createEmpty();
        }
        model.addAttribute("campaign", campaign);
        return "campaign/edit";
    }

    @RequestMapping(value = "/campaign/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam(value = "id", required = false) Integer id) {
        if (id != null) {
            campaignService.delete(id);
        }
        return new ModelAndView("redirect:/campaigns");
    }

    @RequestMapping(value = "/campaign", method = RequestMethod.POST)
    public ModelAndView campaignSave(Campaign campaign, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (campaign.getId() == null) {
                campaignService.insert(campaign);
            } else {
                if (campaignService.findById(campaign.getId()) != null) {
                    campaignService.update(campaign);
                }
            }
        }
        ModelAndView campaignModel = new ModelAndView("campaign/edit");
        campaignModel.addObject("campaign", campaign);
        campaignModel.addObject("history", campaignService.history(campaign.getId()));
        return campaignModel;
    }


    @RequestMapping("/campaigns")
    public String campaigns(Model model) {
        logger.info("Getting all campaigns");
        List<Campaign> campaignList = campaignService.all();
        logger.info("Found {} campaigns", campaignList.size());
        model.addAttribute("campaigns", campaignList);
        model.addAttribute("currentTab", "campaigns");
        return "campaign/list";
    }
}
