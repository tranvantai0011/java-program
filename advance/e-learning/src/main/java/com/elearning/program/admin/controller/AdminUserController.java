package com.elearning.program.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elearning.program.entity.User;
import com.elearning.program.service.RoleService;
import com.elearning.program.service.UserService;

@Controller
@RequestMapping("admin/user")
public class AdminUserController {

  @Autowired
  private UserService userService;
  
  @Autowired
  private RoleService roleService;

  @GetMapping("")
  public String index(ModelMap model) {
    List<User> users = userService.findAll();
    model.addAttribute("users", users);
    return "userList";
  }

  @GetMapping("add")
  public String add( ModelMap model) {
    // userService.save(user);
    model.addAttribute("user", new User());
    model.addAttribute("roles",roleService.findAll());
    return "userAdd";
  }

  @PostMapping("add")
  public String add(ModelMap model,
      @Valid @ModelAttribute("user") User user,
    BindingResult error) {
    if(error.hasErrors()) {
      model.addAttribute("roles",roleService.findAll());
      return "userAdd";
    }
    userService.save(user);
    return "redirect:/admin/user";
  }

  @GetMapping("edit/{id}")
  public String edit(@PathVariable("id") String userId, ModelMap model) {
    User user = userService.findById(userId);
    model.addAttribute("user", user);
    return "userEdit";
  }

  @PostMapping("edit")
  public String edit(@ModelAttribute("user") User user, ModelMap model) {
    userService.update(user);
    return "redirect:/admin/user";
  }

  @GetMapping("delete/{id}")
  public String delete(@PathVariable String id, ModelMap model) {
    userService.delete(id);
    return "redirect:/admin/user";

  }
}
