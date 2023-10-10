package org.spring.dev.company.service.workTime;

import lombok.RequiredArgsConstructor;
import org.spring.dev.company.dto.workTime.WorkTimeDto;
import org.spring.dev.company.entity.member.MemberEntity;
import org.spring.dev.company.entity.worktime.WorkTimeEntity;
import org.spring.dev.company.repository.worktime.WorkTimeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkTimeService {

    private final WorkTimeRepository workTimeRepository;

    public List<WorkTimeDto> getWorkTimeMemberDetail(Long memberId) {
        List<WorkTimeDto> workTimeDtoList = new ArrayList<WorkTimeDto>();
        List<WorkTimeEntity> workTimeList = workTimeRepository.findByMemberEntity_Id(memberId);

        for (WorkTimeEntity workTime : workTimeList) {
            workTimeDtoList.add(WorkTimeDto.toDto(workTime));
        }

        return workTimeDtoList;
    }
    public Long postWorkTimeIn(Long memberId) {
        WorkTimeEntity workTimeEntity = new WorkTimeEntity();
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberId);
        workTimeEntity.setMemberEntity(memberEntity);
        workTimeEntity.setWorkTimeStart(LocalDateTime.now());
        Long result = workTimeRepository.save(workTimeEntity).getId();
        return result;
    }

    /*
    퇴근
     */
    public Long postWorkTimeOut(Long memberId) {

        String date = LocalDate.now().toString();
        WorkTimeEntity workTimeEntity = Optional.ofNullable(workTimeRepository.getDayTime(date))
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("null값 입니다.");
                });

        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberId);
        workTimeEntity.setMemberEntity(memberEntity);
        workTimeEntity.setWorkTimeStart(LocalDateTime.now());
        Long result = workTimeRepository.save(workTimeEntity).getId();
        return result;
    }

    public List<WorkTimeDto> getWorkTimeWorkList(String workMonth, String workType) {
        List<WorkTimeDto> workTimeDtoList = new ArrayList<>();
        if (workType.isEmpty()) {
            List<WorkTimeEntity> workTimeEntityList = workTimeRepository.findByWorkTimeMonth(workMonth);
            // 달만 선택
        }
        List<WorkTimeEntity> workTimeEntityList = workTimeRepository.findByWorkTimeWorkType(workMonth, workType);
        // 달&유형 선택

        if (!workTimeEntityList.isEmpty()) {
            for (WorkTimeEntity workTimeEntity : workTimeEntityList) {
                WorkTimeDto workTimeDto = WorkTimeDto.toDto(workTimeEntity);

            }
        }
        return workTimeDtoList;
    }
}
