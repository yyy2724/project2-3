package org.spring.dev.company.service.approval;

import lombok.RequiredArgsConstructor;
import org.spring.dev.company.dto.approval.response.ApprovalResponse;
import org.spring.dev.company.entity.approval.ApprovalEntity;
import org.spring.dev.company.entity.member.MemberEntity;
import org.spring.dev.company.entity.util.ApproType;
import org.spring.dev.company.exception.ApprovalException;
import org.spring.dev.company.exception.MemberNotFound;
import org.spring.dev.company.repository.approval.ApprovalRepository;
import org.spring.dev.company.repository.member.MemberRepository;
import org.spring.dev.company.service.approval.request.ApprovalServiceCreate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApprovalService {

    private final ApprovalRepository approvalRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public void create(Long id, ApprovalServiceCreate create) {

        MemberEntity member = memberRepository.findById(id).orElseThrow(MemberNotFound::new);

        ApprovalEntity approval = create.toEntity(member);

        approvalRepository.save(approval);
    }


        @Transactional
        public void approval (Long memberId, Long approvalId, String request, LocalDateTime start){

            MemberEntity member = memberRepository.findById(memberId).orElseThrow(MemberNotFound::new);

            ApprovalEntity approval = approvalRepository.findById(approvalId).orElseThrow(ApprovalException::new);

            if (approval.getType().equals(member.getGrade())) {
                approval.approval(approval, request, start);

            } else throw new IllegalArgumentException("결재 권한이 없습니다.");

        }

        public List<ApprovalResponse> list() {

            List<ApprovalEntity> approval = approvalRepository.findAll();

            return approval.stream()
                    .map(ApprovalResponse::of)
                    .collect(Collectors.toList());
        }

        public ApprovalResponse get (Long id){

            ApprovalEntity approval = approvalRepository.findById(id).orElseThrow(ApprovalException::new);

            return ApprovalResponse.of(approval);
        }

        @Transactional
        public void projectComplete(Long id, String complete, LocalDateTime end) {
            ApprovalEntity approval = approvalRepository.findById(id).orElseThrow(ApprovalException::new);

            if (complete.equals("COMPLETE")) approval.complete(end);
        }



}
