package org.spring.dev.company.service.schedule;


import lombok.AllArgsConstructor;
import org.spring.dev.company.dto.schedule.ScheduleDto;
import org.spring.dev.company.entity.schedule.ScheduleEntity;
import org.spring.dev.company.repository.schedule.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleService {

    private ScheduleRepository scheduleRepostory;

    public List<ScheduleDto> getScheduleDetail(ScheduleDto scheduleDto) {
        List<ScheduleDto> scheduleDtoList = new ArrayList<ScheduleDto>();
        List<ScheduleEntity> scheduleEntityList = scheduleRepostory.findScheduleSearch(scheduleDto);

        //비어있지 않다면
        if(!scheduleEntityList.isEmpty())
            for (ScheduleEntity scheduleEntity: scheduleEntityList) {
                scheduleDtoList.add(ScheduleDto.toDto(scheduleEntity));
        }else{
                // throw 발생시켜서 에러 처리
            return null;
        }
        return scheduleDtoList;
    }


    //스케줄 생성
    public ScheduleDto postSchedule(ScheduleDto scheduleDto) {

        ScheduleEntity scheduleEntity = scheduleRepostory.insertSchedule(scheduleDto);



        return scheduleDto;

    }
}
