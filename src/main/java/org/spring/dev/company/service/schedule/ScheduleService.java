package org.spring.dev.company.service.schedule;


import lombok.AllArgsConstructor;
import org.spring.dev.company.dto.schedule.ScheduleDto;
import org.spring.dev.company.entity.schedule.ScheduleEntity;
import org.spring.dev.company.repository.schedule.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleService {

    private ScheduleRepository scheduleRepostory;

    public ScheduleDto getScheduleDetail() {

        List<ScheduleEntity> scheduleEntityList = scheduleRepostory.findAllSchedule();

        for (ScheduleEntity scheduleEntity: scheduleEntityList) {
            System.out.println(scheduleEntity.getId());
            System.out.println(scheduleEntity.getContent());
            System.out.println(scheduleEntity.getType());
            System.out.println(scheduleEntity.getColor());
            System.out.println(scheduleEntity.getMemberId());
            System.out.println(scheduleEntity.getEndDateTime());
            System.out.println(scheduleEntity.getStartDateTime());
        }

        return null;
    }
}
