package org.spring.dev.company.dto.approval.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.spring.dev.company.entity.approval.ApprovalStatus;
import org.spring.dev.company.entity.approval.ApprovalEntity;
import org.spring.dev.company.entity.member.MemberEntity;
import org.spring.dev.company.entity.util.ApproType;

@Getter
@NoArgsConstructor
public class ApprovalResponse {

    private Long id;

    private String title;

    private String content;

    private ApproType type;

    private ApprovalStatus status;

    @Builder
    private ApprovalResponse(Long id, String title, String content, ApproType type, ApprovalStatus status) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.type = type;
        this.status = status;
    }

    //    @Builder
//    private ApprovalResponse(Long id, String title, String content, ApproType type, MemberEntity member, ApprovalStatus status) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//        this.type = type;
//        this.memberEntity = member;
//        this.status = status;
//    }

    public static ApprovalResponse of(ApprovalEntity approval) {
        return ApprovalResponse.builder()
                .id(approval.getId())
                .title(approval.getTitle())
                .content(approval.getContent())
                .type(approval.getType())
                .status(approval.getStatus())
                .build();
    }
}
