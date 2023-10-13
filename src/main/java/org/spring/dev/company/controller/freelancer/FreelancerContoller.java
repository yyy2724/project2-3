package org.spring.dev.company.controller.freelancer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/freelancer")
@RequiredArgsConstructor
@Controller
public class FreelancerContoller {


    @GetMapping("/join")
    public String freeJoin(){
        return "freelancer/join";
    }
    @GetMapping("/login")
    public String freeLogin(){ return "freelancer/login"; }

    @PostMapping("/join")
    public String postFreeJoin(@ModelAttribute FreelancerDto freelancerDto){
        Long rs = freelancerService.freelancerJoin(freelancerDto);
        if(rs !=0){
            return "freelancer/login";
        }
        return "freelancer/join";
    }

    @GetMapping("/detail/{id}")
    public String detailFree(@PathVariable("id")Long freeId, Model model){
        FreelancerDto freelancerDto = freelancerService.freeDetail(freeId);
        model.addAttribute("freeDto",freelancerDto);
        return "freelancer/detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id")Long freeId, Model model){
        FreelancerDto freelancerDto = freelancerService.freeDetail(freeId);
        model.addAttribute("freeDto",freelancerDto);
        return "freelancer/update";
    }

    @PostMapping("/update/{freeId}")
    public String updateFree(@ModelAttribute FreelancerDto freelancerDto,Model model){
        FreelancerDto freelancerDto1 = freelancerService.updateFree(freelancerDto);
        model.addAttribute("freeDto",freelancerDto1);
        return "freelancer/detail";
    }



//    @GetMapping("/oauth2add")
//    public String oauth2addOk(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model){
//
//        System.out.println("myUserDetails " + myUserDetails);
//
//        Long freeId = myUserDetails.getFreelancerEntity().getId();
//
//        System.out.println("myUserDetails.getMemberEntity" + myUserDetails.getFreelancerEntity());
//        System.out.println("myUserDetails.getMemberEntity.getId" + myUserDetails.getFreelancerEntity().getId());
//
//        FreelancerDto freelancerDto = freelancerService.freeUpdateOk(freeId);
//
//        System.out.println("freelancerDto " + freelancerDto);
//        model.addAttribute("freelancer", freelancerDto);
//
//        return "freelancer/oauth2add";
//
//    }
//
//    @PostMapping("/oauth2add")
//    public String oauth2add(@AuthenticationPrincipal MyUserDetails myUserDetails, FreelancerDto freelancerDto){
//        int rs = freelancerService.freeUpdate(freelancerDto);
//
//
//        return "redirect:/";
//    }




}
