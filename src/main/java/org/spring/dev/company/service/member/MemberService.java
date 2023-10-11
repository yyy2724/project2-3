package org.spring.dev.company.service.member;

import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.spring.dev.company.dto.member.MemberDto;
import org.spring.dev.company.entity.member.MemberEntity;
import org.spring.dev.company.repository.member.MemberRepository;
import org.spring.dev.company.repository.member.UserRepository;
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

    public int emailCheck(String email) {

        int rs = memberRepository.findByEmail(email);
        return rs;
    }

    public int nickNameCheck(String nickName) {

        int rs = memberRepository.findByNickName(nickName);

        return rs;
    }

    public int phoneNumCheck(String phone) {
        int rs = memberRepository.findByPhone(phone);

        return rs;
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
}
