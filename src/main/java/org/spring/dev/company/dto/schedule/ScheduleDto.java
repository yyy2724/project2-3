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

        private LocalDateTime startDateTime;

        private LocalDateTime endDateTime;

        private String content;

        private String color;

        private String type;

        private Long memberId;

        private LocalDateTime createTime;

        private LocalDateTime updateTime;

        private Integer is_display;

        public static ScheduleDto toDto(ScheduleEntity scheduleEntity){
                return ScheduleDto.builder()
                        .id(scheduleEntity.getId())
                        .startDateTime(scheduleEntity.getStartDateTime())
                        .endDateTime(scheduleEntity.getEndDateTime())
                        .content(scheduleEntity.getContent())
                        .color(scheduleEntity.getColor())
                        .type(scheduleEntity.getType())
                        .memberId(scheduleEntity.getMemberId())
                        .createTime(scheduleEntity.getCreateTime())
                        .updateTime(scheduleEntity.getUpdateTime())
                        .is_display(scheduleEntity.getIs_display())
                        .build();
        }



}
