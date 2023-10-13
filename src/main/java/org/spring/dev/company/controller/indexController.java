package org.spring.dev.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class indexController {

    /*
    추후 멤버정보 가져오는거 수정필요
     */
    @GetMapping({"/","/index"})
    public String index(){
        return "index";
    }
}
