package org.spring.dev.company.repository.schedule;

import org.spring.dev.company.entity.schedule.ScheduleEntity;
import org.spring.dev.company.entity.worktime.WorkTimeEntity;
import org.spring.dev.company.service.schedule.ScheduleService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ScheduleRepository{

    List<ScheduleEntity> findAllSchedule();

}
