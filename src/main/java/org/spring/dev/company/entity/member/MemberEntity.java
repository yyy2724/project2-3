package org.spring.dev.company.entity.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.spring.dev.company.entity.util.ApproType;
import org.spring.dev.company.entity.util.BaseEntity;
import org.spring.dev.company.entity.util.GenderEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "c_member")
@Getter
@Setter
public class MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String name;

    @Column(name = "member_birth")
    private String birth;

    @Column(name = "member_phone")
    private String phone;

    @Column(name = "member_address")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_grade")
    private ApproType grade;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_gender")
    private GenderEntity gender;

    @Builder
    public MemberEntity(Long id, String name, String birth, String phone, String address, ApproType grade, GenderEntity gender) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.address = address;
        this.grade = grade;
        this.gender = gender;
    }
}
