package org.spring.dev.openApi.naver.controller;


import lombok.RequiredArgsConstructor;
import org.spring.dev.openApi.naver.service.NaverService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/naver")
@RequiredArgsConstructor
public class NaverController {

   private final NaverService naverService;

    @GetMapping("/index")
    public String index(){

        return "naverWork/naver";
    }

    /*

    네이버 로그인 창 띄우서 로그인 하면 지정한 return url로 code값을 가져온다
     */

    @GetMapping("/code")
    @ResponseBody
    public Map<String, Object> getNaverCode(){
        String naverAuthHtml = naverService.getNaverCode();
        Map<String, Object> result = new HashMap<String,Object>();
        result.put("return",naverAuthHtml);
        return result;
    }


// 토큰을 발급 받아서 db에 저장
    @GetMapping("/token")
    @ResponseBody
    public Map<String, Object> getNaverToken(
            @RequestParam("code") String code,
            @RequestParam("errorCode") String errorCode,
            @RequestParam("state") String state
    ){
        String naverAuthHtml = naverService.getNaverToken(code, errorCode, state);
        Map<String, Object> result = new HashMap<String,Object>();
        result.put("return",naverAuthHtml);
        return result;
    }
}