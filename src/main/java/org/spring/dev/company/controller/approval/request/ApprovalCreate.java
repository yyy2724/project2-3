package org.spring.dev.company.controller.approval.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.spring.dev.company.entity.util.ApproType;
import org.spring.dev.company.service.approval.request.ApprovalServiceCreate;

import java.lang.reflect.Type;

@Getter
@NoArgsConstructor
public class ApprovalCreate {

    private String title;

    private String content;

    private ApproType type;

    private Long approvalMemberId;

    @Builder
    public ApprovalCreate(String title, String content, ApproType type,Long approvalMemberId) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.approvalMemberId = approvalMemberId;
    }

    public ApprovalServiceCreate toServiceRequest() {
        return ApprovalServiceCreate.builder()
                .title(title)
                .content(content)
                .type(type)
                .build();
    }
}
