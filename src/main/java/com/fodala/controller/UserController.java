package com.fodala.controller;

import com.fodala.pojo.User;
import com.fodala.pojo.UserImage;
import com.fodala.service.UserImageService;
import com.fodala.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserImageService userImageService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(@RequestParam(value = "id", required = false) Integer id, Model model) {
        User user;
        if (id != null) {
            user = userService.findById(id);
            UserImage userImage = userService.selectImage(id);
            if (userImage == null) {
                userImage = new UserImage();
            }
            model.addAttribute("history", userService.history(id));
            model.addAttribute("data", userImage.getPhoto());
        } else {
            user = userService.createEmpty();
        }
        model.addAttribute("user", user);
        return "user/edit";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ModelAndView userSave(User user, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            if (user.getId() == null) {
                userService.insert(user);
            } else {
                if (userService.findById(user.getId()) != null) {
                    userService.update(user);
                }
            }
        }
        ModelAndView campaignModel = new ModelAndView("user/edit");
        campaignModel.addObject("user", user);
        campaignModel.addObject("history", userService.history(user.getId()));
        return campaignModel;
    }

    @RequestMapping(value = "/user/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id != null) {
            userService.delete(id);
        }
        return new ModelAndView("redirect:/users");
    }

    @RequestMapping("/users")
    public String users(Model model) {
        logger.info("Getting all users");
        List<User> userList = userService.all();
        logger.info("Found {} users", userList.size());
        model.addAttribute("users", userList);
        model.addAttribute("currentTab", "users");
        return "user/list";
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        return authentication.getName();
    }
}
