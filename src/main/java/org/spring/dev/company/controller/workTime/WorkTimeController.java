package org.spring.dev.company.controller.workTime;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.spring.dev.company.dto.workTime.WorkTimeDto;
import org.spring.dev.company.service.workTime.WorkTimeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/work")
public class WorkTimeController {

    private final WorkTimeService workTimeService;

    /*
    member 값을 받으면 해당 맴버에 대한 근무 기록을 보여주는 컨트롤러

    추후 search값을 받아서 해당기간동안 근무기록 등 추가하면 좋음!
     */

    @GetMapping("/{memberId}")
    @ResponseBody
    public Map<String, Object> getWorkTimeMemberDetail(
            @PathVariable("memberId") Long memberId
    ){
        Map<String, Object> map = new HashMap<>();
        List<WorkTimeDto> workTimeDtoList = workTimeService.getWorkTimeMemberDetail(memberId);
        map.put("workTime",workTimeDtoList);
        return map;
    }

    @PostMapping("/workin")
    @ResponseBody
    public Map<String, Object> postWorkIn(
            @RequestParam("memberId") Long memberId
    ){
        Map<String, Object> map = new HashMap<>();
      Long result = workTimeService.postWorkTimeIn(memberId);
//        map.put("workTime",workTimeDtoList);
        return map;
    }

    @PostMapping("/workout")
    @ResponseBody
    public Map<String, Object> postWorkOut(
            @RequestParam("memberId") Long memberId
    ) {
        Map<String, Object> map = new HashMap<>();
        Long result = workTimeService.postWorkTimeOut(memberId);
//        map.put("workTime",workTimeDtoList);
        return map;
    }
    /*
    list
     */
    @GetMapping("/{memberId}/list")
    public String getWorkTimeWorklist(
            @PathVariable("memberId") Long memberId,
            @RequestParam(value = "workMonth", required = false) String workMonth,
            @RequestParam(value = "workType", required = false) String workType){

        List<WorkTimeDto> workTimeList = workTimeService.getWorkTimeWorkList(workMonth, workType);

        return "workTime/list";
    }

}
