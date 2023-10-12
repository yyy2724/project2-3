package org.spring.dev.company.service.approval;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.spring.dev.company.controller.approval.request.ApprovalCreate;
import org.spring.dev.company.entity.approval.ApprovalEntity;
import org.spring.dev.company.entity.approval.ApprovalStatus;
import org.spring.dev.company.entity.member.MemberEntity;
import org.spring.dev.company.entity.util.ApproType;
import org.spring.dev.company.entity.util.GenderEntity;
import org.spring.dev.company.repository.approval.ApprovalRepository;
import org.spring.dev.company.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApprovalServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ApprovalService approvalService;

    @Autowired
    private ApprovalRepository approvalRepository;

    @AfterEach
    public void clean() {
        approvalRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("결재 받을 서류를 생성한다.")
    public void test1() {
        // given
        MemberEntity member1 = memberSave("김신입", "0418", "010-0000-0000", "주소1", ApproType.INTERN, GenderEntity.M);


        MemberEntity member2 = memberSave("김부장", "0418", "010-1111-1111", "주소2", ApproType.INTERN, GenderEntity.M);

        List<MemberEntity> memberEntities = memberRepository.saveAll(List.of(member1, member2));


        ApprovalCreate request = ApprovalCreate.builder()
                .title("휴가 결재 부탁드립니다.")
                .content("휴가 관련")
                .type(ApproType.MANAGER)
                .approvalMemberId(memberEntities.get(0).getId())
                .build();

        // when
        approvalService.create(memberEntities.get(0).getId(), request.toServiceRequest());

        // then
        ApprovalEntity response = approvalRepository.findAll().get(0);
        assertEquals("휴가 결재 부탁드립니다.", response.getTitle());
        assertEquals("휴가 관련", response.getContent());
    }

    @Test
    @DisplayName("인턴이 요청한 결재를 부장이 처리한다.")
    public void test2() {
        // given
        MemberEntity member1 = memberSave("김신입", "0418", "010-0000-0000", "주소1", ApproType.INTERN, GenderEntity.M);

        MemberEntity member2 = memberSave("김부장", "0418", "010-1111-1111", "주소2", ApproType.INTERN, GenderEntity.M);

        List<MemberEntity> memberEntities = memberRepository.saveAll(List.of(member1, member2));


        ApprovalEntity approval = ApprovalEntity.builder()
                .title("휴가 결재 부탁드립니다.")
                .content("휴가 관련")
                .type(ApproType.MANAGER)
                .memberEntity(memberEntities.get(0))
                .approvalMemberId(memberEntities.get(1).getId())
                .build();

        approvalRepository.save(approval);
        String request = "APPROVAL";
        // when
        approvalService.approval(memberEntities.get(1).getId(), approval.getId(), request);

        // then
        ApprovalEntity response = approvalRepository.findAll().get(0);
        assertEquals(ApprovalStatus.APPROVAL, response.getStatus());
    }

    @Test
    @Disabled
    @DisplayName("허가받지 않는 사람이 결재를 요청할 시 에러가 난다.")
    public void test3() {
        // given
        MemberEntity member1 = memberSave("김신입", "0418", "010-0000-0000", "주소1", ApproType.INTERN, GenderEntity.M);

        MemberEntity member2 = memberSave("김부장", "0418", "010-1111-1111", "주소2", ApproType.INTERN, GenderEntity.M);

        List<MemberEntity> memberEntities = memberRepository.saveAll(List.of(member1, member2));

        ApprovalEntity approval = ApprovalEntity.builder()
                .title("휴가 결재 부탁드립니다.")
                .content("휴가 관련")
                .type(ApproType.MANAGER)
                .memberEntity(memberEntities.get(0))
                .approvalMemberId(memberEntities.get(1).getId())
                .build();

        approvalRepository.save(approval);

        String request = "APPROVAL";

        // expect
        assertThrows(IllegalArgumentException.class, () -> approvalService.approval(memberEntities.get(0).getId(), approval.getId(), request));

    }

    @Test
    @DisplayName("부장이 인턴의 결재를 반려한다.")
    public void test4() {
        // given
        MemberEntity member1 = memberSave("김신입", "0418", "010-0000-0000", "주소1", ApproType.INTERN, GenderEntity.M);

        MemberEntity member2 = memberSave("김부장", "0418", "010-1111-1111", "주소2", ApproType.INTERN, GenderEntity.M);

        List<MemberEntity> memberEntities = memberRepository.saveAll(List.of(member1, member2));

        ApprovalEntity approval = ApprovalEntity.builder()
                .title("휴가 결재 부탁드립니다.")
                .content("휴가 관련")
                .type(ApproType.MANAGER)
                .memberEntity(memberEntities.get(0))
                .approvalMemberId(memberEntities.get(1).getId())
                        .build();

        approvalRepository.save(approval);
        String request = "UNAUTHORIZED";
        // when
        approvalService.approval(memberEntities.get(1).getId(), approval.getId(), request);

        // then
        ApprovalEntity response = approvalRepository.findAll().get(0);
        assertEquals(ApprovalStatus.UNAUTHORIZED, response.getStatus());
    }


    private static MemberEntity memberSave(String name, String birth, String phone, String address, ApproType type, GenderEntity gender) {
        return MemberEntity.builder()
                .name(name)
                .birth(birth)
                .phone(phone)
                .address(address)
                .grade(type)
                .gender(gender)
                .build();
    }
}