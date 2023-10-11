package org.spring.dev.company.controller.member;

import lombok.RequiredArgsConstructor;
import org.spring.dev.company.service.member.MemberService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/join")
@RestController
@RequiredArgsConstructor
public class MemberCheck {

    private final MemberService memberService;

    @PostMapping("/emailCheck")
    public @ResponseBody int emailCheck(@RequestParam("email") String email){


        return memberService.emailCheck(email);

    }

    @PostMapping("/nickCheck")
    public @ResponseBody int nickCheck(@RequestParam("nickName") String nickName){

        return memberService.nickNameCheck(nickName);

    }

    @PostMapping("/phoneCheck")
    public @ResponseBody int phoneCheck(@RequestParam("phone") String phone){

        return memberService.phoneNumCheck(phone);

    }
}
