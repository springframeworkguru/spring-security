package com.agharibi.controllers;

import com.agharibi.domain.User;
import com.agharibi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @RequestMapping({"/list", "/"})
    public String listUsers(Model model) {
        model.addAttribute("users", userService.listAll());
        return "user/list";
    }

    @RequestMapping("/show/{id}")
    public String getUser(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user/show";
    }

    @RequestMapping("edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user/userform";
    }

    @RequestMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user/userform";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(User user) {
        user = userService.saveOrUpdate(user);
        return "redirect:/user/show" + user.getId();
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        userService.delete(id);
        return "redirect:/user/list";
    }




    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
