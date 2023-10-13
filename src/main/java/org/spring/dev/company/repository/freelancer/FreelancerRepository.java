package org.spring.dev.company.repository.freelancer;

import org.spring.dev.company.entity.freelancer.FreelancerEntity;
import org.spring.dev.company.entity.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FreelancerRepository extends JpaRepository<FreelancerEntity, Long> {
    @Query("SELECT COUNT(*) FROM FreelancerEntity f WHERE f.email = :email")
    int findByEmail1(String email);
    @Query("SELECT COUNT(*) FROM FreelancerEntity f WHERE f.phone = :phone")
    int findByPhone(String phone);

    Optional<FreelancerEntity> findByEmail(String email);
}
