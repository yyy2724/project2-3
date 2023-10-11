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

        return FreelancerDto.builder()
                .id(freelancerEntity.get().getId())
                .email(freelancerEntity.get().getEmail())
                .name(freelancerEntity.get().getName())
                .phone(freelancerEntity.get().getPhone())
                .grade(freelancerEntity.get().getGrade())
                .gender(freelancerEntity.get().getGender())
                .birth(freelancerEntity.get().getBirth())
                .career(freelancerEntity.get().getCareer())
                .CreateTime(freelancerEntity.get().getCreateTime())
                .UpdateTime(freelancerEntity.get().getUpdateTime())
                .postcode(freelancerEntity.get().getPostcode())
                .address(freelancerEntity.get().getAddress())
                .detailAddress(freelancerEntity.get().getDetailAddress())
                .extraAddress(freelancerEntity.get().getExtraAddress())
                .is_display(freelancerEntity.get().getIs_display())
                .build();
    }

}
