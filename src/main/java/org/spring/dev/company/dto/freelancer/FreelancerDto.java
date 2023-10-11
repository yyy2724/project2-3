package org.spring.dev.company.dto.freelancer;

import lombok.*;
import org.spring.dev.company.entity.util.ApproType;
import org.spring.dev.company.entity.util.GenderEntity;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FreelancerDto {

    private Long id;

    private String name;

    private String birth;

    private String email;

    private String phone;

    private String password;

    private String career;

    private String postcode;

    private String address;

    private String detailAddress;

    private String extraAddress;

    private ApproType grade;

    private GenderEntity gender;

    private int is_display;

    private LocalDateTime CreateTime;

    private LocalDateTime UpdateTime;
}

