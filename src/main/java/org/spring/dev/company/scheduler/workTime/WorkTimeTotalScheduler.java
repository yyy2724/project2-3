package org.spring.dev.company.scheduler.workTime;

import lombok.RequiredArgsConstructor;
import org.spring.dev.company.entity.worktime.WorkTimeEntity;
import org.spring.dev.company.repository.worktime.WorkTimeRepository;
import org.spring.dev.company.service.workTime.WorkTimeService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WorkTimeTotalScheduler {

    private final WorkTimeService workTimeService;
    @Transactional
    //cron 표현식 매일 오전 09:00 실행
//        @Scheduled(cron = "0 00 09 * * ?")
    @Scheduled(fixedRate = 500000)
    public void myScheduledTask() {
        workTimeService.doWorkTotalcal();
    }

}
