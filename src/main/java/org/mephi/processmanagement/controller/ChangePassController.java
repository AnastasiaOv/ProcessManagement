package org.mephi.processmanagement.controller;


import org.mephi.processmanagement.dao.UserDao;
import org.mephi.processmanagement.model.User;
import org.mephi.processmanagement.service.SecurityService;
import org.mephi.processmanagement.service.UserService;
import org.mephi.processmanagement.validator.PassValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by Анастасия on 18.11.2017.
 * контроллер для страницы изменения пароля
 */
@Controller
public class ChangePassController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private PassValidator passValidator;


    @RequestMapping(value = "/change", method = RequestMethod.GET)
    public String change(Model model) {
        User user = userService.findByUsername(securityService.findLoggedInUsername());
        user.setPassword("");
        user.setConfirmPassword("");
        model.addAttribute("userForm", user);
        return "change";
    }

    @Transactional
    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public String change(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model, HttpServletRequest request, HttpServletResponse response, Authentication auth) {
        passValidator.validate(userForm, bindingResult);
        User oldUser = userService.findByUsername(userForm.getUsername());
        userService.resetPassword(oldUser, userForm.getPassword());
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Пароль не был изменен");
        }
        return "user";
    }
}
