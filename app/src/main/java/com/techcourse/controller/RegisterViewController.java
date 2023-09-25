package com.techcourse.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.org.springframework.web.bind.annotation.GetMapping;
import webmvc.org.springframework.web.servlet.ModelAndView;
import webmvc.org.springframework.web.servlet.view.JspView;

@context.org.springframework.stereotype.Controller
public class RegisterViewController {

    @GetMapping("/register/view")
    public ModelAndView execute(final HttpServletRequest req, final HttpServletResponse res) {
        return new ModelAndView(new JspView("/register.jsp"));
    }
}
