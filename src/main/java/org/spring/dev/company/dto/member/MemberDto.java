package org.spring.dev.company.dto.member;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.spring.dev.company.entity.member.MemberEntity;
import org.spring.dev.company.entity.util.ApproType;
import org.spring.dev.company.entity.util.GenderEntity;

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

    private String nickName;

    private String phone;

    private String password;

    private String birth;

    private String postcode;

    private String address;

    private String detailAddress;

    private String extraAddress;

    private ApproType grade;

    private GenderEntity gender;

    private String position;

    private String career;

    @ColumnDefault("1")
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
        memberDto.setNickName(memberEntity.getNickName());
        memberDto.setPhone(memberEntity.getPhone());
        memberDto.setGrade(memberEntity.getGrade());
        memberDto.setCreateTime(memberEntity.getCreateTime());
        memberDto.setUpdateTime(memberEntity.getUpdateTime());

        return memberDto;
    }
}
