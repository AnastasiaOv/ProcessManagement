package org.mephi.processmanagement.controller;

import org.hibernate.Session;
import org.mephi.processmanagement.dao.RoleDao;
import org.mephi.processmanagement.model.Role;
import org.mephi.processmanagement.model.User;
import org.mephi.processmanagement.service.SecurityService;
import org.mephi.processmanagement.service.UserService;
import org.mephi.processmanagement.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Controller for {@link org.mephi.processmanagement.model.User}'s pages.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        List<String> roleNames = new ArrayList<>();
        for(Role r :roleDao.findAll())
            roleNames.add(r.getName());
        model.addAttribute("roleNames", roleNames);
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);
        List<Role> roles = new ArrayList<>();
        for(String s:userForm.getRoleNames())
            roles.add(roleDao.findByName(s));
        userForm.setRoles(roles);
        userService.save(userForm);
        if (bindingResult.hasErrors()) {
            model.addAttribute("error","Ошибка при регистрации");
        }
        return "admin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(Model model) {
        return "user";
    }
}
