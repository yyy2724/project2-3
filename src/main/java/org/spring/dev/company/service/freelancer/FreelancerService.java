package org.spring.dev.company.service.freelancer;

import lombok.RequiredArgsConstructor;
import org.spring.dev.company.dto.freelancer.FreelancerDto;
import org.spring.dev.company.dto.member.MemberDto;
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

    public FreelancerDto updateFree(FreelancerDto freelancerDto) {
        Optional<FreelancerEntity> optionalFreelancerEntity
                = Optional.ofNullable(freelancerRepository.findById(freelancerDto.getId()).orElseThrow(()->{
                    return new IllegalArgumentException("아이디가 없습니다.");
        }));
        if (optionalFreelancerEntity.isPresent()){
            FreelancerEntity freelancer = optionalFreelancerEntity.get();
            freelancer.setBirth(freelancerDto.getBirth());
            freelancer.setPhone(freelancerDto.getPhone());
            freelancer.setCareer(freelancerDto.getCareer());
            freelancer.setPostcode(freelancerDto.getPostcode());
            freelancer.setAddress(freelancerDto.getAddress());
            freelancer.setDetailAddress(freelancerDto.getDetailAddress());
            freelancer.setExtraAddress(freelancerDto.getExtraAddress());
            Long upId = freelancerRepository.save(freelancer).getId();
            if (upId != 0){
                return FreelancerDto.builder()
                        .id(freelancer.getId())
                        .name(freelancer.getName())
                        .email(freelancer.getEmail())
                        .password(freelancer.getPassword())
                        .birth(freelancer.getBirth())
                        .phone(freelancer.getPhone())
                        .postcode(freelancer.getPostcode())
                        .address(freelancer.getAddress())
                        .detailAddress(freelancer.getDetailAddress())
                        .extraAddress(freelancer.getExtraAddress())
                        .gender(freelancer.getGender())
                        .grade(freelancer.getGrade())
                        .career(freelancer.getCareer())
                        .CreateTime(freelancer.getCreateTime())
                        .is_display(freelancer.getIs_display())
                        .build();
            }
        }
             return FreelancerDto.builder()
                     .id(optionalFreelancerEntity.get().getId())
                     .name(optionalFreelancerEntity.get().getName())
                     .email(optionalFreelancerEntity.get().getEmail())
                     .password(optionalFreelancerEntity.get().getPassword())
                     .birth(optionalFreelancerEntity.get().getBirth())
                     .phone(optionalFreelancerEntity.get().getPhone())
                     .postcode(optionalFreelancerEntity.get().getPostcode())
                     .address(optionalFreelancerEntity.get().getAddress())
                     .detailAddress(optionalFreelancerEntity.get().getDetailAddress())
                     .extraAddress(optionalFreelancerEntity.get().getExtraAddress())
                     .gender(optionalFreelancerEntity.get().getGender())
                     .grade(optionalFreelancerEntity.get().getGrade())
                     .career(optionalFreelancerEntity.get().getCareer())
                     .CreateTime(optionalFreelancerEntity.get().getCreateTime())
                     .is_display(optionalFreelancerEntity.get().getIs_display())
                     .build();


    }
}
