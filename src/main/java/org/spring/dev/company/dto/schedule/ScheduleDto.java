package org.spring.dev.company.dto.schedule;

import lombok.*;
import org.spring.dev.company.entity.schedule.ScheduleEntity;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleDto {

        private Long id;

        private LocalDateTime StartDateTime;

        private LocalDateTime EndDateTime;

        private String Content;

        private String color;

        private String Type;

        private Long memberId;

        private LocalDateTime CreateTime;

        private LocalDateTime UpdateTime;

        private Integer is_display;

        public static ScheduleDto toDto(ScheduleEntity scheduleEntity){
                return ScheduleDto.builder()
                        .id(scheduleEntity.getId())
                        .StartDateTime(scheduleEntity.getStartDateTime())
                        .EndDateTime(scheduleEntity.getEndDateTime())
                        .Content(scheduleEntity.getContent())
                        .color(scheduleEntity.getColor())
                        .Type(scheduleEntity.getType())
                        .memberId(scheduleEntity.getMemberId())
                        .CreateTime(scheduleEntity.getCreateTime())
                        .UpdateTime(scheduleEntity.getUpdateTime())
                        .is_display(scheduleEntity.getIs_display())
                        .build();
        }



}
