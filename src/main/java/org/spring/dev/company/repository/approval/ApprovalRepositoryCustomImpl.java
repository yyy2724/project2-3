package org.spring.dev.company.repository.approval;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.spring.dev.company.dto.approval.response.ApprovalResponse;
import org.spring.dev.company.entity.approval.ApprovalEntity;
import org.spring.dev.company.entity.approval.QApprovalEntity;
import org.spring.dev.company.service.approval.request.ApprovalServiceSearch;

import java.util.List;

@RequiredArgsConstructor
public class ApprovalRepositoryCustomImpl implements ApprovalRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<ApprovalEntity> getList(ApprovalServiceSearch search) {

        QApprovalEntity approval = QApprovalEntity.approvalEntity;

        return jpaQueryFactory.selectFrom(approval)
                .limit(search.getSize())
                .offset(search.getOffset())
                .orderBy(approval.id.desc())
                .fetch();

    }
}
