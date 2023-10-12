package org.spring.dev.company.service.pay;

import lombok.RequiredArgsConstructor;
import org.spring.dev.company.dto.pay.PayDto;
import org.spring.dev.company.entity.member.MemberEntity;
import org.spring.dev.company.entity.pay.PayEntity;
import org.spring.dev.company.entity.worktime.WorkTimeEntity;
import org.spring.dev.company.repository.pay.PayRepository;
import org.spring.dev.company.repository.worktime.WorkTimeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PayService {

    private final WorkTimeRepository workTimeRepository;
    private final PayRepository payRepository;

    public Integer postPayList(Long memberId, String workMonth) {
        PayEntity payEntity = new PayEntity();
        MemberEntity memberEntity = new MemberEntity();
        List<WorkTimeEntity> workTimeEntityList = workTimeRepository.findByWorkTimeMonth(memberId, workMonth);

        Integer sum = 0;
        for(WorkTimeEntity workTimeEntity : workTimeEntityList){
            sum += workTimeEntity.getTotal(); // total 계산
        }
        Integer pay = (sum / 60) * 10000; // 월급 계산

        memberEntity.setId(memberId); // memberId가져오기
        payEntity.setMonthly(pay); // 월급 저장
        payEntity.setIsPay(1); // 월급 지급 여부 설정 1
        payEntity.setIs_display(1); //
        payEntity.setMemberEntity(memberEntity); // member정보 저장
        payEntity.setPayDay(LocalDate.now()); // 월급 기록 당일 저장

        Optional<Long> payId = Optional.ofNullable(payRepository.save(payEntity).getId());

        //값이 존재
        if (payId.isPresent()) {
            return 1;
        }
        return 0;

    }

    public List<PayDto> getPayMonthlyList(Long memberId) {
        List<PayDto> payDtoList = new ArrayList<>();
        List<PayEntity> payEntityList  = payRepository.findBymemberEntity_Id(memberId);

        if(!payEntityList.isEmpty()){
            for(PayEntity payEntity : payEntityList){
                PayDto payDto = PayDto.toDto(payEntity);
                payDtoList.add(payDto);
            }
        }
        return payDtoList;
    }
}
