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

//@Component
//@RequiredArgsConstructor
//public class WorkTimeTotalScheduler {
//
//    private final WorkTimeRepository workTimeRepository;
//        @Transactional
//        @Scheduled(fixedRate = 500000)
//        public void myScheduledTask() {
//
//           String date = LocalDate.now().minusDays(1).toString();
//           System.out.println(date);
//           WorkTimeEntity workTimeEntity = Optional.ofNullable(workTimeRepository.getDayTime(date))
//                   .orElseThrow(() -> {
//                       throw new IllegalArgumentException("null값 입니다.");
//                   });
//
//           if (workTimeEntity.getWorkTimeStart() != null && workTimeEntity.getWorkTimeEnd() != null){
//               Duration total = Duration.between(workTimeEntity.getWorkTimeStart(), workTimeEntity.getWorkTimeEnd());
//               long workTimeTotal =  total.toMinutes();
////               workTimeEntity.setTotal((int) workTimeTotal);
//               workTimeRepository.updateWorkTotalTime(workTimeTotal, date);
//           }
//        }
//}
