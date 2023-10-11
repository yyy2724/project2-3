package org.spring.dev.company.service.freelancer;

import lombok.RequiredArgsConstructor;
import org.spring.dev.company.dto.freelancer.FreelancerDto;
import org.spring.dev.company.entity.freelancer.FreelancerEntity;
import org.spring.dev.company.repository.freelancer.FreelancerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FreelancerService {

    private final FreelancerRepository freelancerRepository;
    private final PasswordEncoder passwordEncoder;
    public Long freelancerJoin(FreelancerDto freelancerDto) {

        FreelancerEntity freelancerEntity = FreelancerEntity.toFreelancer(freelancerDto, passwordEncoder);

        Long rs = freelancerRepository.save(freelancerEntity).getId();
        freelancerRepository.findById(rs).orElseThrow(()->{
            return new IllegalArgumentException("회원가입 실패");
        });
        return  rs;
    }

    public FreelancerDto freeDetail(Long freeId) {

        Optional<FreelancerEntity> freelancerEntity =
                Optional.ofNullable(freelancerRepository.findById(freeId).orElseThrow(()->{
                    return new IllegalArgumentException("아이디를 찾을 수 없습니다.");
                }));
        return null;
    }

    public int emailCheck(String email) {
        int rs;
        return rs = freelancerRepository.findByEmail(email);
    }

    public int phoneCheck(String phone) {
        int rs;
        return rs = freelancerRepository.findByPhone(phone);
    }
}
