package org.spring.dev.company.repository.approval;

import org.spring.dev.company.entity.approval.ApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalRepository extends JpaRepository<ApprovalEntity, Long> {
}
