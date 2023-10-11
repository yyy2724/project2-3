package org.spring.dev.company.controller.free;

import lombok.RequiredArgsConstructor;
import org.spring.dev.company.dto.freelancer.FreelancerDto;
import org.spring.dev.company.service.freelancer.FreelancerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/free")
@RequiredArgsConstructor
@Controller
public class FreelancerContoller {

    private final FreelancerService freelancerService;

    @GetMapping("/join")
    public String freeJoin(){
        return "freelancer/join";
    }

    // DB에 있는지 확인
    @PostMapping("/emailCheck")
    public @ResponseBody int emailCheck(@RequestParam("email") String email){

        return freelancerService.emailCheck(email);
    }
    @PostMapping("/phoneCheck")
    public @ResponseBody int phoneCheck(@RequestParam("phone") String phone){

        return freelancerService.phoneCheck(phone);
    }



    @PostMapping("/join")
    public String postFreeJoin(@ModelAttribute FreelancerDto freelancerDto){
        Long rs = freelancerService.freelancerJoin(freelancerDto);
        if(rs !=1){
            return "free/join";
        }
        return "member/login";
    }

    @GetMapping("/detail/{id}")
    public String detailFree(@PathVariable("id")Long freeId, Model model){
        FreelancerDto freelancerDto = freelancerService.freeDetail(freeId);
        model.addAttribute("freeDto",freelancerDto);
        return "freelancer/detail";
    }

}
