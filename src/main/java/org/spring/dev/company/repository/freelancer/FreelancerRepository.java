package org.spring.dev.company.repository.freelancer;

import org.spring.dev.company.entity.freelancer.FreelancerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FreelancerRepository extends JpaRepository<FreelancerEntity, Long> {
    @Query("SELECT COUNT(*) FROM FreelancerEntity f WHERE f.email = :email")
    int findByEmail(String email);
    @Query("SELECT COUNT(*) FROM FreelancerEntity f WHERE f.phone = :phone")
    int findByPhone(String phone);
}
