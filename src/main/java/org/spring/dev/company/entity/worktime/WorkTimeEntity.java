package org.spring.dev.company.entity.worktime;

import lombok.Getter;
import lombok.Setter;
import org.spring.dev.company.entity.member.MemberEntity;
import org.spring.dev.company.entity.util.BaseEntity;
import org.spring.dev.company.entity.util.WorkType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "c_work_time")
public class WorkTimeEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "work_time_in")
    private LocalDateTime workTimeStart;

    @Column(name = "work_time_out")
    private LocalDateTime workTimeEnd;

    @Column(name = "work_time_total")
    private Integer total;

    // 근무형태
    @Enumerated(EnumType.STRING)
    @Column(name = "work_time_type")
    private WorkType workType;

    @JoinColumn(name = "memberEntity")
    @ManyToOne
    private MemberEntity memberEntity;


}
