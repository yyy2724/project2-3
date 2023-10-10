package org.spring.dev.company.dto.approval.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.spring.dev.company.entity.approval.ApprovalStatus;
import org.spring.dev.company.entity.approval.ApprovalEntity;
import org.spring.dev.company.entity.util.ApproType;

@Getter
@NoArgsConstructor
public class ApprovalResponse {

    private Long id;

    private String title;

    private String content;

    private ApproType type;

//    private MemberEntity memberEntity;

    private ApprovalStatus status;

    private Long approvalMemberId;

    @Builder
    private ApprovalResponse(Long id, String title, String content, ApproType type, ApprovalStatus status, Long approvalMemberId) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.status = status;
        this.approvalMemberId = approvalMemberId;
    }

    public static ApprovalResponse of(ApprovalEntity approval) {
        return ApprovalResponse.builder()
                .id(approval.getId())
                .title(approval.getTitle())
                .content(approval.getContent())
                .type(approval.getType())
                .status(approval.getStatus())
                .approvalMemberId(approval.getApprovalMemberId())
                .build();
    }

    @Builder
    public ApprovalResponse(String title, String content, Long approvalMemberId) {
        this.title = title;
        this.content = content;
        this.approvalMemberId = approvalMemberId;
    }
}
