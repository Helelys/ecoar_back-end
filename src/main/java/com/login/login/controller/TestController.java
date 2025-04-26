package com.login.login.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
    @GetMapping("/test")
    public String teste() {
        System.out.println("✅ Rota /test chamada!");
        return "OK";
    }
}