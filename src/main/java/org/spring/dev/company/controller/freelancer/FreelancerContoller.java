package org.spring.dev.company.controller.freelancer;

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


}
