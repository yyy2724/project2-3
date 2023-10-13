package org.spring.dev.company.dto.member;

import lombok.*;
import org.spring.dev.company.entity.member.MemberEntity;
import org.spring.dev.company.entity.util.ApproType;
import org.spring.dev.company.entity.util.GenderEntity;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String password;

    private String birth;

    private String postcode;

    private String address;

    private String detailAddress;

    private String extraAddress;

    private ApproType grade;

    private GenderEntity gender;

    private String career;

    //회사 관련
    private String companyName;
    // 사업자번호
    private String businessNumber;

    private int is_display;

    private LocalDateTime CreateTime;

    private LocalDateTime UpdateTime;

    private boolean matching;

    public MemberDto(boolean matching) {
        this.matching = matching;
    }

    public static MemberDto toMemberDto(MemberEntity memberEntity) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(memberEntity.getId());
        memberDto.setEmail(memberEntity.getEmail());
        memberDto.setName(memberEntity.getName());
        memberDto.setPhone(memberEntity.getPhone());
        memberDto.setGrade(memberEntity.getGrade());
        memberDto.setCreateTime(memberEntity.getCreateTime());
        memberDto.setUpdateTime(memberEntity.getUpdateTime());

        return memberDto;
    }
}
