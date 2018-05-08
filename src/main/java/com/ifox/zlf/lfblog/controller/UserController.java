package com.ifox.zlf.lfblog.controller;

import com.ifox.zlf.lfblog.entity.UserEO;
import com.ifox.zlf.lfblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ModelAndView list(Model model){
        model.addAttribute("list",userRepository.findAll());
        return new ModelAndView("user/userlist","userlist",model);
    }

    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id")Integer id,Model model){
        UserEO userEO = userRepository.findById(id).get();
        model.addAttribute("user",userEO);
        return new ModelAndView("user/view","userModel",model);
    }

    @GetMapping("/form")
    public ModelAndView creteForm(Model model){
        model.addAttribute("user",new UserEO());
        return new ModelAndView("user/form","userModel",model);
    }
    @PostMapping
    public ModelAndView saveOrUpdateUser(UserEO userEO){
        userRepository.save(userEO);
        return new ModelAndView("redirect:/user");
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id")Integer id){
        userRepository.deleteById(id);
        return new ModelAndView("redirect:/user");
    }

    @GetMapping("/modifi/{id}")
    public ModelAndView modifi(@PathVariable("id")Integer id, Model model){
        UserEO userEO = userRepository.findById(id).get();
        model.addAttribute("user",userEO);
        return new ModelAndView("user/form","userModel",model);
    }
}
