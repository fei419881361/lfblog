package com.ifox.zlf.lfblog.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public ModelAndView hello(Model model){
        return new ModelAndView("hello","usermodel",model);
    }
}
