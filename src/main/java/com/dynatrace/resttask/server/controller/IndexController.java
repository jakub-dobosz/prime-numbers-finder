package com.dynatrace.resttask.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        return "index";
    }
}
