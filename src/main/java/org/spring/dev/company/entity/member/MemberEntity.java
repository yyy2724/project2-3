package org.spring.dev.company.entity.member;

import lombok.*;
import org.spring.dev.company.dto.member.MemberDto;
import org.spring.dev.company.entity.util.ApproType;
import org.spring.dev.company.entity.util.BaseEntity;
import org.spring.dev.company.entity.util.GenderEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "c_member")
public class MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String name;

    @Column(name = "member_birth")
    private String birth;

    @Column(name = "member_email")
    private String email;

    @Column(name = "member_nick")
    private String nickName;

    @Column(name = "member_phone")
    private String phone;

    @Column(name = "member_password")
    private String password;

    @Column(name = "member_postcode")
    private String postcode;

    @Column(name = "member_address")
    private String address;

    @Column(name = "member_detailAddress")
    private String detailAddress;

    @Column(name = "member_extraAddress")
    private String extraAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_grade")
    private ApproType grade;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_gender")
    private GenderEntity gender;

    // 직책
    @Column(name = "member_position")
    private String position;

    @Column(name = "member_matching")
    private boolean matching;

    public static MemberEntity toMember(MemberDto memberDto, PasswordEncoder passwordEncoder) {

        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setName(memberDto.getName());
        memberEntity.setNickName(memberDto.getNickName());
        memberEntity.setEmail(memberDto.getEmail());
        memberEntity.setPhone(memberDto.getPhone());
        memberEntity.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        memberEntity.setBirth(memberDto.getBirth());
        memberEntity.setPostcode(memberDto.getPostcode());
        memberEntity.setAddress(memberDto.getAddress());
        memberEntity.setDetailAddress(memberDto.getDetailAddress());
        memberEntity.setExtraAddress(memberDto.getExtraAddress());
        memberEntity.setGrade(ApproType.INTERN);
        memberEntity.setGender(memberDto.getGender());
        memberEntity.setIs_display(memberDto.getIs_display());
        return memberEntity;
    }

}
