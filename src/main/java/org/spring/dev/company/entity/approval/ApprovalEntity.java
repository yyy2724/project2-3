package org.spring.dev.company.entity.approval;

import org.spring.dev.company.entity.member.MemberEntity;
import org.spring.dev.company.entity.util.ApproType;
import org.spring.dev.company.entity.util.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "c_approval")
public class ApprovalEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "approval_id")
    private Long id;

    @Column(name = "approval_title")
    private String title;

    @Column(name = "approval_content")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_type")
    private ApproType type;

    // n:1 한명의 회원이 여러개의 결재문서
    @JoinColumn(name = "memberEntity")
    @ManyToOne
    private MemberEntity memberEntity;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;

    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    private LocalDateTime start;

    private LocalDateTime end;


    @Builder
    private ApprovalEntity(Long id, String title, String content, ApproType type, MemberEntity memberEntity, ApprovalStatus status, ProjectStatus projectStatus, LocalDateTime start, LocalDateTime end) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.memberEntity = memberEntity;
        this.status = status;
        this.projectStatus = projectStatus != null ? ProjectStatus.COMPLETE : ProjectStatus.INCOMPLETE;
        this.start = start;
        this.end = end;
    }

    public void approval(ApprovalEntity approval, String request, LocalDateTime start) {
        if (request.equals("APPROVAL")) {
            approval.start = start;
            approval.status = ApprovalStatus.APPROVAL;
        }

        if (request.equals("UNAUTHORIZED")) approval.status = ApprovalStatus.UNAUTHORIZED;
    }

    public void complete(LocalDateTime end) {
        this.end = end;
        this.projectStatus = ProjectStatus.COMPLETE;
    }

}
