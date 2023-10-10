package org.spring.dev.company.controller.approval;

import lombok.RequiredArgsConstructor;
import org.spring.dev.company.controller.approval.request.ApprovalCreate;
import org.spring.dev.company.dto.approval.response.ApprovalResponse;
import org.spring.dev.company.service.approval.ApprovalService;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class ApprovalController {

    private ApprovalService approvalService;

    @PostMapping("api/v1/approval/create/{memberId}")
    public void create(@RequestBody @Valid ApprovalCreate create, @PathVariable Long memberId) {
        approvalService.create(memberId, create.toServiceRequest());
    }

    @PostMapping("api/v1/approval/{approveId}")
    public void approval(Long memberId, @PathVariable("approveId") Long approvalId, String request) {
        approvalService.approval(memberId, approvalId, request);
    }

    @GetMapping("api/v1/approval/{approvalId}")
    public ApprovalResponse get(@PathVariable("approvalId") Long id) {
        return approvalService.get(id);
    }

}
