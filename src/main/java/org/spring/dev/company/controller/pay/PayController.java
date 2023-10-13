package org.spring.dev.company.controller.pay;

import lombok.RequiredArgsConstructor;
import org.spring.dev.company.dto.pay.PayDto;
import org.spring.dev.company.service.pay.PayService;
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
    @PostMapping("/{memberId}")
    public Map<String,Object> getMemberPayPrice(
            @PathVariable("memberId") Long memberId,
            @RequestParam(value = "workMonth", required = false) String workMonth){

        // 달에 해당하는 근무기록 가져오기
        Integer result = payService.postPayList(memberId, workMonth);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result", result);
        return map;
    }

//    월급 달마다 보여주기
//    total만 보여주는지 다 보여주는지 -> 다 보여주는 거로 결정!
//    정산하기 버튼 누르면 나오는거??
    @GetMapping("/{memberId}/list")
    public Map<String, Object> getMemberPayMontly(
            @PathVariable("memberId") Long memberId){

        // 월급 list로 가져오기
        List<PayDto> payDtoList = payService.getPayMonthlyList(memberId);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("payList", payDtoList);
        return map;
    }
}
