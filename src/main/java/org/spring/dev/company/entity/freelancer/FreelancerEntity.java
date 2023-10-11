package org.spring.dev.company.entity.freelancer;

import lombok.*;
import org.spring.dev.company.dto.freelancer.FreelancerDto;
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
@Table(name = "c_freeLan")
public class FreelancerEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "free_id")
    private Long id;

    @Column(name = "free_name")
    private String name;

    @Column(name = "free_birth")
    private String birth;

    @Column(name = "free_email")
    private String email;

    @Column(name = "free_phone")
    private String phone;

    @Column(name = "free_password")
    private String password;

    @Column(name = "free_career")
    private String career;

    @Column(name = "free_postcode")
    private String postcode;

    @Column(name = "free_address")
    private String address;

    @Column(name = "free_detailAddress")
    private String detailAddress;

    @Column(name = "free_extraAddress")
    private String extraAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "free_grade")
    private ApproType grade;

    @Enumerated(EnumType.STRING)
    @Column(name = "free_gender")
    private GenderEntity gender;

    public static FreelancerEntity toFreelancer(FreelancerDto freelancerDto, PasswordEncoder passwordEncoder) {
        FreelancerEntity freelancerEntity = new FreelancerEntity();
        freelancerEntity.setEmail(freelancerDto.getEmail());
        freelancerEntity.setName(freelancerDto.getName());
        freelancerEntity.setPassword(passwordEncoder.encode(freelancerDto.getPassword()));
        freelancerEntity.setBirth(freelancerDto.getBirth());
        freelancerEntity.setPhone(freelancerDto.getPhone());
        freelancerEntity.setCareer(freelancerDto.getCareer());
        freelancerEntity.setGender(freelancerDto.getGender());
        freelancerEntity.setGrade(ApproType.FREELANCER);
        freelancerEntity.setPostcode(freelancerDto.getPostcode());
        freelancerEntity.setAddress(freelancerDto.getAddress());
        freelancerEntity.setDetailAddress(freelancerDto.getDetailAddress());
        freelancerEntity.setExtraAddress(freelancerDto.getExtraAddress());
        freelancerEntity.setIs_display(1);
        return freelancerEntity;
    }
}
