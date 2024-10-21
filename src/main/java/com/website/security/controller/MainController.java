package com.website.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@CrossOrigin
@Slf4j
public class MainController {

    @GetMapping("/")
    public String mainPage(){
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("MainController 유저의 ID는 : "+accountId);
        return "안녕하세요 "+accountId + "님!";
    }
}
