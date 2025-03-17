package com.example.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {

    @GetMapping("/secure")
    public String secureEndpoint() {
        return "!** 인증된 사용자만 접근 가능 **!";
    }

    @GetMapping("/public")
    public String publicEndpoint() {
        return "누구나 접근 가능!";
    }
}
