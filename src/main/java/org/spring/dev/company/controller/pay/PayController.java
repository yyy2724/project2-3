package org.spring.dev.company.controller.pay;

import lombok.RequiredArgsConstructor;
import org.spring.dev.company.config.MyUserDetails;
import org.spring.dev.company.dto.pay.PayDto;
import org.spring.dev.company.dto.workTime.WorkTimeDto;
import org.spring.dev.company.service.pay.PayService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pay")
public class PayController {

    private final PayService payService;

    // 정산하기 버튼 눌렀을 떄 들어가기
//    월급 달마다
    @PostMapping("/{memberId}")
    public Map<String,Object> getMemberPayMontly(
            @PathVariable("memberId") Long memberId,
            @RequestParam(value = "workMonth", required = false) String workMonth){

        // 달에 해당하는 근무기록 가져오기
        Integer result = payService.postPayList(memberId, workMonth);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result", result);
        return map;
    }
    @GetMapping("/{memberId}/yearList")
    public List<PayDto> getMemberPayYearly(
            @PathVariable("memberId") Long memberId,
            @RequestParam(value = "workYear", required = false) String workYear){

        // 년에 해당하는 근무기록 가져오기
        List<PayDto> result = payService.getPayYearList(memberId, workYear);

        return result;
    }

// 해당 멤버에 모든 월급내역 보여주기
    @GetMapping("/free/{memberId}/list")
    public Map<String, Object> getMemberPayPrice(
            @PathVariable("memberId") Long memberId){

        // 월급 list로 가져오기
        List<PayDto> payDtoList = payService.getPayMonthlyList(memberId);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("payList", payDtoList);
        return map;
    }

    @PostMapping("/free/{approveId}")
    public void postFRpay(@AuthenticationPrincipal MyUserDetails member,
                       @PathVariable(name = "approveId") Long approveId){
            payService.postFRpay(approveId,member);
    }
}
