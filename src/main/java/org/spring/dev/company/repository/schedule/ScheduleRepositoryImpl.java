package org.spring.dev.company.repository.schedule;


import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.spring.dev.company.entity.schedule.QScheduleEntity;
import org.spring.dev.company.entity.schedule.ScheduleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<ScheduleEntity> findAllSchedule() {
        QScheduleEntity qScheduleEntity = QScheduleEntity.scheduleEntity;

        List<ScheduleEntity> scheduleEntityList = jpaQueryFactory
                .select(qScheduleEntity)
                .from(qScheduleEntity)
                .where(qScheduleEntity.memberId.eq(1L))
                .fetch();
        return scheduleEntityList;

    }
}
