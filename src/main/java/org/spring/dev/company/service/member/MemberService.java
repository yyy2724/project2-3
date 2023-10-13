package org.spring.dev.company.service.member;

import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.spring.dev.company.dto.member.MemberDto;
import org.spring.dev.company.entity.member.MemberEntity;
import org.spring.dev.company.repository.freelancer.FreelancerRepository;
import org.spring.dev.company.repository.member.MemberRepository;
import org.spring.dev.company.repository.member.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FreelancerRepository freelancerRepository;

    public int emailCheck(String email) {

        int memberRs = memberRepository.findByEmail(email);
        int freeRs = freelancerRepository.findByEmail(email);
        if (memberRs == 0){
            if (freeRs == 1){
                return 1;
            } else if (freeRs == 0) {
                return 0;
            }
        }else if (memberRs == 1){
            return 1;
        }
        return 0;
    }

    public int nickNameCheck(String nickName) {

        int rs = memberRepository.findByNickName(nickName);

        return rs;
    }

    public int phoneNumCheck(String phone) {
        int memberRs = memberRepository.findByPhone(phone);
        int freeRs = freelancerRepository.findByPhone(phone);
        if (memberRs == 0){
            if (freeRs == 1){
                return 1;
            } else if (freeRs == 0) {
                return 0;
            }
        }else if (memberRs == 1){
            return 1;
        }
        return 0;
    }

    @Transactional
    public Long memberJoin(MemberDto memberDto) {


        MemberEntity memberEntity = MemberEntity.toMember(memberDto, passwordEncoder);
        Long rs = memberRepository.save(memberEntity).getId();

        memberRepository.findById(rs).orElseThrow(IllegalArgumentException::new);
        System.out.println(rs);
        return rs;
    }

    public MemberDto detailMember(Long memberId) {
        Optional<MemberEntity> memberEntity =
                Optional.ofNullable(memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new));

        return MemberDto.builder()
                .id(memberEntity.get().getId())
                .name(memberEntity.get().getName())
                .nickName(memberEntity.get().getNickName())
                .email(memberEntity.get().getEmail())
                .password(memberEntity.get().getPassword())
                .birth(memberEntity.get().getBirth())
                .phone(memberEntity.get().getPhone())
                .postcode(memberEntity.get().getPostcode())
                .address(memberEntity.get().getAddress())
                .detailAddress(memberEntity.get().getDetailAddress())
                .extraAddress(memberEntity.get().getExtraAddress())
                .gender(memberEntity.get().getGender())
                .grade(memberEntity.get().getGrade())
                .position(memberEntity.get().getPosition())
                .CreateTime(memberEntity.get().getCreateTime())
                .is_display(memberEntity.get().getIs_display())
                .build();
    }

    public boolean userEmailCheck(String email, String name) {

        MemberEntity memberEntity = memberRepository.findUserByEmail(email);

        if (memberEntity != null && memberEntity.getName().equals(name)) {
            return true;
        } else {
            return false;
        }

    }

    public void SetTempPassword(String memberEmail, String newPassword) {

        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(memberEmail);
        if (optionalMemberEntity.isPresent()) {

            MemberEntity memberEntity = optionalMemberEntity.get();
            String password = passwordEncoder.encode(newPassword);
            memberEntity.setPassword(password);
            memberRepository.save(memberEntity);

        } else {
//            log
        }
    }

    public boolean checkEmailNameMatching(String email, String name) {
        MemberEntity memberEntity = memberRepository.findByEmailAndName(email, name);
        return memberEntity != null;


    }

    @Transactional
    public MemberDto updateMember(MemberDto memberDto) {
        Optional<MemberEntity> memberEntity =
                Optional.ofNullable(memberRepository.findById(memberDto.getId()).orElseThrow(IllegalArgumentException::new));


        MemberEntity memberEntity1 = memberEntity.get();
        memberEntity1.setId(memberDto.getId());
        memberEntity1.setName(memberDto.getName());
        memberEntity1.setNickName(memberDto.getNickName());
        memberEntity1.setPhone(memberDto.getPhone());
        memberEntity1.setGender(memberDto.getGender());
        memberEntity1.setPostcode(memberDto.getPostcode());
        memberEntity1.setAddress(memberDto.getAddress());
        memberEntity1.setDetailAddress(memberDto.getDetailAddress());
        memberEntity1.setExtraAddress(memberDto.getExtraAddress());



        Long upId = memberRepository.save(memberEntity1).getId();

        if (upId != 0) {
            return MemberDto.builder()
                    .id(memberEntity1.getId())
                    .name(memberEntity1.getName())
                    .nickName(memberEntity1.getNickName())
                    .email(memberEntity1.getEmail())
                    .password(memberEntity1.getPassword())
                    .birth(memberEntity1.getBirth())
                    .phone(memberEntity1.getPhone())
                    .postcode(memberEntity1.getPostcode())
                    .address(memberEntity1.getAddress())
                    .detailAddress(memberEntity1.getDetailAddress())
                    .extraAddress(memberEntity1.getExtraAddress())
                    .gender(memberEntity1.getGender())
                    .grade(memberEntity1.getGrade())
                    .position(memberEntity1.getPosition())
                    .CreateTime(memberEntity1.getCreateTime())
                    .is_display(memberEntity1.getIs_display())
                    .build();
        }
            return MemberDto.builder()
                .id(memberEntity1.getId())
                .name(memberEntity1.getName())
                .nickName(memberEntity1.getNickName())
                .email(memberEntity1.getEmail())
                .password(memberEntity1.getPassword())
                .birth(memberEntity1.getBirth())
                .phone(memberEntity1.getPhone())
                .postcode(memberEntity1.getPostcode())
                .address(memberEntity1.getAddress())
                .detailAddress(memberEntity1.getDetailAddress())
                .extraAddress(memberEntity1.getExtraAddress())
                .gender(memberEntity1.getGender())
                .grade(memberEntity1.getGrade())
                .position(memberEntity1.getPosition())
                .CreateTime(memberEntity1.getCreateTime())
                .is_display(memberEntity1.getIs_display())
                .CreateTime(memberEntity1.getCreateTime())
                .UpdateTime(memberEntity1.getUpdateTime())
                .build();



    }

    public Long disabledMember(MemberDto memberDto) {
        Optional<MemberEntity> memberEntity =
                Optional.ofNullable(memberRepository.findById(memberDto.getId()).orElseThrow(IllegalArgumentException::new));
        if (memberEntity.get().getIs_display() != 0) {

            MemberEntity memberEntity1 = memberEntity.get();
            memberEntity1.setIs_display(0);
            Long rs = memberRepository.save(memberEntity1).getId();
            return rs;
        }
        Long rs = 0L;
        return rs;
    }

    public Page<MemberDto> pageMemberList(Pageable pageable, String subject, String search) {

        Page<MemberEntity> memberEntities = null;

        if (subject == null){
            memberEntities = memberRepository.findAll(pageable);
        }else if (subject.equals("name")) {
            memberEntities = memberRepository.findByNameContains(pageable,search);
        } else if (subject.equals("email")) {
            memberEntities = memberRepository.findByEmailContains(pageable,search);
        } else if (subject.equals("phone")){
            memberEntities = memberRepository.findByPhoneContains(pageable,search);
        } else {
            memberEntities = memberRepository.findAll(pageable);
        }

        Page<MemberDto> memberDtos = memberEntities.map(MemberDto::toMemberDto);

        return memberDtos;
    }

    public MemberDto passwordChange(MemberDto memberDto) {
        Optional<MemberEntity> optionalMemberEntity
                = Optional.ofNullable(memberRepository.findById(memberDto.getId()).orElseThrow(IllegalArgumentException::new));

        MemberEntity memberEntity = optionalMemberEntity.get();
        memberEntity.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        memberRepository.save(memberEntity);
        return MemberDto.builder()
                .id(memberEntity.getId())
                .name(memberEntity.getName())
                .nickName(memberEntity.getNickName())
                .email(memberEntity.getEmail())
                .password(memberEntity.getPassword())
                .birth(memberEntity.getBirth())
                .phone(memberEntity.getPhone())
                .postcode(memberEntity.getPostcode())
                .address(memberEntity.getAddress())
                .detailAddress(memberEntity.getDetailAddress())
                .extraAddress(memberEntity.getExtraAddress())
                .gender(memberEntity.getGender())
                .grade(memberEntity.getGrade())
                .position(memberEntity.getPosition())
                .CreateTime(memberEntity.getCreateTime())
                .is_display(memberEntity.getIs_display())
                .build();
    }

    public boolean passwordCheck(Long memberId, String password) {
        Optional<MemberEntity> optionalMemberEntity
                = Optional.ofNullable(memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new));

        if (optionalMemberEntity.isPresent()){
            MemberEntity memberEntity = optionalMemberEntity.get();
            return passwordEncoder.matches(password, memberEntity.getPassword());
        }else {
            return false;
        }

    }
}
