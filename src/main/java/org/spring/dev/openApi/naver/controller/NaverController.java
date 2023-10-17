package org.spring.dev.openApi.naver.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/naver")
public class NaverController {

    @GetMapping("/index")
    public String index(){

        return "naverWork/naver";
    }

    @GetMapping("/code")
    public String getAuthCode(String code, String state, Model model){
        System.out.println(code);
        System.out.println(state);
        System.out.println(model);
        return "naverWork/naver";
    }
}
