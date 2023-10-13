package org.spring.dev.company.service.freelancer;

import lombok.RequiredArgsConstructor;
import org.spring.dev.company.config.MyUserDetails;
import org.spring.dev.company.dto.freelancer.FreelancerDto;
import org.spring.dev.company.dto.member.MemberDto;
import org.spring.dev.company.entity.freelancer.FreelancerEntity;
import org.spring.dev.company.entity.member.MemberEntity;
import org.spring.dev.company.repository.freelancer.FreelancerRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FreelancerService {

    private final FreelancerRepository freelancerRepository;
    private final PasswordEncoder passwordEncoder;

    public Long freelancerJoin(FreelancerDto freelancerDto) {

        FreelancerEntity freelancerEntity = FreelancerEntity.toFreelancer(freelancerDto, passwordEncoder);

        Long rs = freelancerRepository.save(freelancerEntity).getId();
        freelancerRepository.findById(rs).orElseThrow(() -> {
            return new IllegalArgumentException("회원가입 실패");
        });
        return rs;
    }

    public FreelancerDto freeDetail(Long freeId) {

        Optional<FreelancerEntity> freelancerEntity =
                Optional.ofNullable(freelancerRepository.findById(freeId).orElseThrow(() -> {
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
                = Optional.ofNullable(freelancerRepository.findById(freelancerDto.getId()).orElseThrow(() -> {
            return new IllegalArgumentException("아이디가 없습니다.");
        }));
        if (optionalFreelancerEntity.isPresent()) {
            FreelancerEntity freelancer = optionalFreelancerEntity.get();
            freelancer.setBirth(freelancerDto.getBirth());
            freelancer.setPhone(freelancerDto.getPhone());
            freelancer.setCareer(freelancerDto.getCareer());
            freelancer.setPostcode(freelancerDto.getPostcode());
            freelancer.setAddress(freelancerDto.getAddress());
            freelancer.setDetailAddress(freelancerDto.getDetailAddress());
            freelancer.setExtraAddress(freelancerDto.getExtraAddress());
            Long upId = freelancerRepository.save(freelancer).getId();
            if (upId != 0) {
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


    @Transactional
    public FreelancerDto freeUpdateOk(Long freeId) {
        // MemberEntity id 확인
        Optional<FreelancerEntity> optionalMemberEntity =
                Optional.ofNullable(freelancerRepository.findById(freeId).orElseThrow(() -> {
                    return new IllegalArgumentException("수정할 아이디가 없습니다.");
                }));

        FreelancerEntity freeEntity = optionalMemberEntity.get();

        if (optionalMemberEntity.isPresent()) {

            FreelancerDto freelancerDto = FreelancerDto.builder()
                    .id(freeEntity.getId())
                    .name(freeEntity.getName())
                    .birth(freeEntity.getBirth())
                    .email(freeEntity.getEmail())
                    .career(freeEntity.getCareer())
                    .phone(freeEntity.getPhone())
                    .postcode(freeEntity.getPostcode())
                    .address(freeEntity.getAddress())
                    .detailAddress(freeEntity.getDetailAddress())
                    .extraAddress(freeEntity.getExtraAddress())
                    .grade(freeEntity.getGrade())
                    .gender(freeEntity.getGender())
                    .password(passwordEncoder.encode(freeEntity.getPassword()))
                    .build();
            return freelancerDto;
        }

        return null;

    }


    @Transactional
    public int freeUpdate(FreelancerDto freelancerDto) {
        Optional<FreelancerEntity>  optionalMemberEntity=
                Optional.ofNullable(freelancerRepository.findById(freelancerDto.getId()).orElseThrow(() -> {
                    return new IllegalArgumentException("수정할 아이디가 없습니다.");
                }));

        FreelancerEntity freelancerEntity = FreelancerEntity.builder()
                .id(freelancerDto.getId())
                .name(freelancerDto.getName())
                .birth(freelancerDto.getBirth())
                .email(freelancerDto.getEmail())
                .career(freelancerDto.getCareer())
                .phone(freelancerDto.getPhone())
                .postcode(freelancerDto.getPostcode())
                .address(freelancerDto.getAddress())
                .detailAddress(freelancerDto.getDetailAddress())
                .extraAddress(freelancerDto.getExtraAddress())
                .grade(freelancerDto.getGrade())
                .gender(freelancerDto.getGender())
                .password(passwordEncoder.encode(freelancerDto.getPassword()))
                .build();


        Long freeId = freelancerRepository.save(freelancerEntity).getId();

        Optional<FreelancerEntity> optionalMemberEntity2=
                Optional.ofNullable(freelancerRepository.findById(freeId).orElseThrow(() -> {
                    return new IllegalArgumentException("수정 아이디가 없습니다.");
                }));

        if(optionalMemberEntity2.isPresent()){
            // 수정이 정상 실행
            return 1;
        }
        return 0;
    }
}
