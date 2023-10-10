package org.spring.dev.company.service.approval.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.spring.dev.company.entity.approval.ApprovalEntity;
import org.spring.dev.company.entity.util.ApproType;

@Getter
@NoArgsConstructor
public class ApprovalServiceCreate {

    private String title;

    private String content;

    private ApproType type;

    private Long approvalMemberId;

    @Builder
    private ApprovalServiceCreate(String title, String content, ApproType type, Long approvalMemberId) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.approvalMemberId = approvalMemberId;
    }

    public ApprovalEntity toEntity(Long id) {
        return ApprovalEntity.builder()
                .title(title)
                .content(content)
                .type(type)
                .approvalMemberId(id)
                .build();
    }
}
