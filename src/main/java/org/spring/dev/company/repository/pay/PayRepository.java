package org.spring.dev.company.repository.pay;

import org.spring.dev.company.entity.pay.PayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRepository extends JpaRepository<PayEntity, Long> {
}
