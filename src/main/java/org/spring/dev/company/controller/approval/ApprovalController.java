package org.spring.dev.company.controller.approval;

import lombok.RequiredArgsConstructor;
import org.spring.dev.company.config.MyUserDetails;
import org.spring.dev.company.controller.approval.request.ApprovalCreate;
import org.spring.dev.company.controller.approval.request.ApprovalSearch;
import org.spring.dev.company.dto.approval.response.ApprovalResponse;
import org.spring.dev.company.service.approval.ApprovalService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class ApprovalController {

    private final ApprovalService approvalService;

    @PostMapping("/api/v1/approval/create")
    public void create(@RequestBody @Valid ApprovalCreate request, @AuthenticationPrincipal MyUserDetails member) {
        approvalService.create(member.getMemberEntity().getId(), request.toServiceRequest());
    }

    @PostMapping("/api/v1/approval/{approveId}")
    public void approval(@AuthenticationPrincipal MyUserDetails member, @PathVariable("approveId") Long approvalId,@RequestBody String request) {
        LocalDateTime start = LocalDateTime.now();
        approvalService.approval(member.getMemberEntity().getId(), approvalId, request, start);
    }

    @GetMapping("/api/v1/approval")
    public List<ApprovalResponse> list(@ModelAttribute ApprovalSearch approvalSearch) {
        return approvalService.list(approvalSearch.toServiceRequest());
    }

    @GetMapping("/api/v1/approval/{approvalId}")
    public ApprovalResponse get(@PathVariable("approvalId") Long id) {
        return approvalService.get(id);
    }

}
