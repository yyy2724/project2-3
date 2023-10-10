package org.spring.dev.company.repository.worktime;

import org.spring.dev.company.entity.worktime.WorkTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
public interface WorkTimeRepository extends JpaRepository<WorkTimeEntity, Long> {
//    List<WorkTimeEntity> findByMemberEntity_MemberId(Long memberId);

    List<WorkTimeEntity> findByMemberEntity_Id(Long memberId);

    WorkTimeEntity findByWorkTimeStart(LocalDateTime workTime);


    @Query(value = "SELECT * " +
            "FROM c_work_time " +
            "WHERE DATE(work_time_in) = :date", nativeQuery = true)
    WorkTimeEntity getDayTime(@Param("date") String date);


    @Modifying
    @Query(value = "UPDATE c_work_time " +
            "SET work_time_total = :workTimeTotal " +
            "WHERE DATE(work_time_in) = :date", nativeQuery = true)
    void updateWorkTotalTime(@Param("workTimeTotal") Long workTimeTotal, @Param("date") String date);


//    달 검색
    @Modifying
    @Query(value = "SELECT * "+
    "FROM c_work_time " +
    "WHERE MONTH(work_time_in) = :month", nativeQuery = true)
    List<WorkTimeEntity> findByWorkTimeMonth(@Param("month") String workMonth);

    @Modifying
    @Query(value = "SELECT * "+
            "FROM c_work_time " +
            "WHERE MONTH(work_time_in) = :month "+
            "AND work_time_type LIKE '%:type%'", nativeQuery = true)
    List<WorkTimeEntity> findByWorkTimeWorkType(@Param("month") String workMonth, @Param("type") String workType);

}
