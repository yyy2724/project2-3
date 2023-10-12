package org.spring.dev.company.controller.schedule;

import lombok.AllArgsConstructor;
import org.spring.dev.company.dto.schedule.ScheduleDto;
import org.spring.dev.company.service.schedule.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("schedule")
@AllArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;


    /*
        멤버 아이디를 필수로 개인 스퀘줄 정보를 가져옴
        검색 조건에 따라 가져올수있게 설정
     */
    @GetMapping("/{memberId}")
    public Map<String, Objects> getScheduleDetail(
            @PathVariable(name = "memberId") Long memberId){

        Map<String,Objects> result = new HashMap<String,Objects>();

            scheduleService.getScheduleDetail();


        return result;
    }

}
