package org.spring.dev.company.controller.member;

import lombok.RequiredArgsConstructor;
import org.spring.dev.company.config.MyUserDetails;
import org.spring.dev.company.dto.member.MemberDto;
import org.spring.dev.company.entity.member.MemberEntity;
import org.spring.dev.company.entity.util.ApproType;
import org.spring.dev.company.service.member.AdminMemberService;
import org.spring.dev.company.service.member.EmailService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;


@RequestMapping("/member")
@RequiredArgsConstructor
@Controller
public class AdminMemberController {

    private final AdminMemberService memberService;
    private final EmailService emailService;


    @GetMapping("/login")
    public String login() {
        // 관리자 로그인
        return "member/login";
    }

    @GetMapping("/companyLogin")
    public String companyLogin(){
        // 회사로그인
        return "company/companyLogin";
    }
    @GetMapping("/freelancerLogin")
    public String freelancerLogin(){
        // 프리렌서 로그인
        return "freelancer/freelancerLogin";
    }



    @GetMapping("/join")
    public String adminJoin() {
        // 관리자 회원가입페이지
        return "member/join";
    }

    @PostMapping("/adminJoin")
    public String memberJoin(@ModelAttribute MemberDto memberDto,
                             @RequestParam(name = "memberImg", required = false)List<MultipartFile> memberImg) {
//        System.out.println(memberDto.getEmail());

            Long rs = memberService.memberJoin(memberDto);
        if (rs == 0) {
            return "member/join";
        }
        return "member/login";
    }

    @GetMapping("/freeJoin")
    public String freeJoin() {
        // 프리렌서 회원가입
        return "freelancer/join";
    }

    @PostMapping("/freeJoin")
    public String freeJoinPost(@ModelAttribute MemberDto memberDto) {
//        System.out.println(memberDto.getEmail());

        Long rs = memberService.freeJoin(memberDto);
        if (rs == 0) {
            return "freelancer/join";
        }
        return "member/login";
    }

    @GetMapping("/companyJoin")
    public String companyJoin() {
        // 회사 회원가입
        return "company/join";
    }

    @PostMapping("/companyJoin")
    public String companyJoinPost(@ModelAttribute MemberDto memberDto) {
        Long rs = memberService.companyJoin(memberDto);
        if (rs == 0) {
            return "company/join";
        }
        return "member/login";
    }

    @GetMapping("/m")
    public String m() {
        return "member/m";
    }


    @GetMapping("/detail/{id}")
    public String detailMember(@PathVariable("id") Long memberId, Model model, MyUserDetails myUserDetails) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")) || auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_STAFF"))) {
            MemberDto memberDto = memberService.detailMember(memberId);
            model.addAttribute("memberDto", memberDto);
            return "member/detail";
        }else {
            return "member/error";
        }
    }


    @GetMapping("/up/{id}")
    public String updateMember(@PathVariable("id") Long memberId, Model model, MyUserDetails myUserDetails) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")) || auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_STAFF"))) {
            MemberDto memberDto = memberService.detailMember(memberId);
            model.addAttribute("memberDto", memberDto);
            return "member/update";
        }else {
            return "member/error";
        }
    }

    @GetMapping("/findPw")
    public String findPwPage() {

        return "member/findPw";
    }

    @ResponseBody
    @PostMapping("/check")
    public int sendEmail(HttpServletRequest request, String email) {
        HttpSession session = request.getSession();
        emailService.mailSend(session, email);
        return 123;
    }

    @ResponseBody
    @PostMapping("/emailCheck")
    public boolean emailCertification(HttpServletRequest request, String email, String inputCode) {
        HttpSession session = request.getSession();

        boolean result = emailService.emailCertification(session, email, Integer.parseInt(inputCode));

//        return emailService.emailCertification(session, email, Integer.parseInt(inputCode));
        return result;
    }

    // 비번 찾기 - 새로운 비번 설정
    @PostMapping("/checkOk")
    public String checkOk(String email, String password) {
        emailService.updatePassword(email, password);

        return "member/login";
    }

    @PostMapping("/checkOk1")
    public void checkOk1(String email, String password) {
        emailService.updatePassword(email, password);

    }

    @GetMapping("/checkOk")
    public String checkOk(@RequestParam(value = "email") String email, Model model) {

        System.out.println(email + "이메일");
        model.addAttribute("email", email);


        return "member/checkOk";
    }


    @PostMapping("/update/{id}")
    public String upMember(@ModelAttribute MemberDto memberDto, Model model, @PathVariable("id") Long memberId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            MemberDto memberDto1 = memberService.updateMember(memberDto, memberId);
            model.addAttribute("memberDto", memberDto1);
            return "member/detail";
        }else{
            return "member/error";
        }
    }

    @GetMapping("/disabled/{memberId}")
    public String disabled(@PathVariable("memberId")Long memberId, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
        MemberDto memberDto = memberService.detailMember(memberId);
        model.addAttribute("memberDto", memberDto);
        return "member/disabled";
        }else{
            return "member/error";
        }
    }


    @PostMapping("/disabled/{memberId}")
    public String disMember(@PathVariable("memberId") Long memberId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
        int rs = memberService.disabledMember(memberId);
        if (rs != 0) {
        return "member/detail";
        }
            return "member/m";
        }else{
            return "member/error";
        }
    }


    // 비밀번호 변경
    @GetMapping("/pwChange/{memberId}")
    public String pwChange(@PathVariable("memberId") Long id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            MemberDto memberDto = memberService.detailMember(id);
            model.addAttribute("memberDto", memberDto);
            return "member/pwChange";
        }else {
            return "member/error";
        }
    }

    @PostMapping("/pwChange/{memberId}")
    public String pwChangePost(@ModelAttribute MemberDto memberDto, @PathVariable("memberId") Long memberId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            int rs = memberService.passwordChange(memberDto, memberId);
            if (rs != 1) {
                MemberDto memberDto1 = memberService.detailMember(memberId);
                model.addAttribute("memberDto", memberDto1);
                return "member/detail";
            }
            return "redirect:/member/logout";
        }else {
            return "member/error";
        }
    }

    @PostMapping("/passCheck")
    public @ResponseBody boolean passwordCheck(@RequestParam("id") Long memberId, @RequestParam("password") String password) {

        boolean rs = memberService.passwordCheck(memberId, password);
        System.out.println(rs);
        return rs;
    }


    @GetMapping("/oauth2add")
    public String oauth2addOk(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model) {

        System.out.println("myUserDetails " + myUserDetails);

        Long id = myUserDetails.getMemberEntity().getId();

        System.out.println("myUserDetails.getMemberEntity" + myUserDetails.getMemberEntity());
        System.out.println("myUserDetails.getMemberEntity.getId" + myUserDetails.getMemberEntity().getId());

        MemberDto memberDto = memberService.memberUpdateOk(id);

        System.out.println("memberDto " + memberDto);
        model.addAttribute("memberDto", memberDto);

        return "member/oauth2add";

    }

    @PostMapping("/oauth2add")
    public String oauth2add(@AuthenticationPrincipal MyUserDetails myUserDetails, MemberDto memberDto) {
        int rs = memberService.memberUpdate(memberDto);

        return "redirect:/";
    }


    @GetMapping("/freeDetail/{memberId}")
    public String freeDetail(@PathVariable("memberId") Long memberId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            MemberDto memberDto = memberService.detailMember(memberId);

            model.addAttribute("memberDto", memberDto);
            return "freelancer/detail";
        }else{
            return "member/error";
        }
    }


    @GetMapping("/freeUp/{memberId}")
    public String freeUp(@PathVariable("memberId") Long memberId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            MemberDto memberDto = memberService.detailMember(memberId);
            model.addAttribute("memberDto", memberDto);
            return "freelancer/update";
        }else{
            return "member/error";
        }
    }


    @PostMapping("/freeUpdate/{memberId}")
    public String freeUpdate(@ModelAttribute MemberDto memberDto, @PathVariable("memberId") Long memberId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            MemberDto memberDto1 = memberService.freeUpdate(memberDto, memberId);
            model.addAttribute("memberDto", memberDto1);
            return "freelancer/detail";
        }else {
            return "member/error";
        }

    }


    @GetMapping("/companyDetail/{memberId}")
    public String companyDetail(@PathVariable("memberId") Long memberId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            MemberDto memberDto = memberService.companyDetail(memberId);
            model.addAttribute("memberDto", memberDto);
            return "company/detail";
        }else {
            return "member/error";
        }

    }

    @GetMapping("/companyUp/{memberId}")
    public String companyUp(@PathVariable("memberId") Long memberId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")) || auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_STAFF"))) {
            MemberDto memberDto = memberService.companyDetail(memberId);
            model.addAttribute("memberDto", memberDto);
            return "company/update";
        }else {
            return "member/error";
        }
    }

    @PostMapping("/companyUpdate/{memberId}")
    public String companyUpdatePost(@PathVariable("memberId") Long memberId, @ModelAttribute MemberDto memberDto, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")) || auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_STAFF"))) {
            MemberDto memberDto1 = memberService.companyUpdate(memberDto, memberId);
            model.addAttribute("memberDto", memberDto1);
            return "company/detail";
        } else {
            return "member/error";
        }
    }


    //    여기부터 LIST
    @GetMapping("/freeList")
    public String freeList(@PageableDefault(page = 0, size = 5, sort = "id",
            direction = Sort.Direction.DESC) Pageable pageable,
                           @RequestParam(value = "subject", required = false) String subject,
                           @RequestParam(value = "search", required = false) String search,
                           Model model) {

        Page<MemberDto> memberList = memberService.pageFreeList(pageable, subject, search);


        Long totalCount = memberList.getTotalElements();
        int pagesize = memberList.getSize();
        int nowPage = memberList.getNumber();
        int totalPage = memberList.getTotalPages();
        int blockNum = 3;

        int startPage =
                (int) ((Math.floor(nowPage / blockNum) * blockNum) + 1 <= totalPage
                        ? (Math.floor(nowPage / blockNum) * blockNum) + 1 : totalPage);

        int endPage =
                (startPage + blockNum - 1 < totalPage ? startPage + blockNum - 1 : totalPage);


        for (int i = startPage; i <= endPage; i++) {
            System.out.println(i + " , ");
        }

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
//        model.addAttribute("postVo", postList);


        model.addAttribute("freeList", memberList);


        return "freelancer/freeLancerList";
    }

    @GetMapping("/companyList")
    public String companyList(@PageableDefault(page = 0, size = 5, sort = "id",
            direction = Sort.Direction.DESC) Pageable pageable,
                              @RequestParam(value = "subject", required = false) String subject,
                              @RequestParam(value = "search", required = false) String search,
                              Model model) {

        Page<MemberDto> memberList = memberService.pageCompanyList(pageable, subject, search);


        Long totalCount = memberList.getTotalElements();
        int pagesize = memberList.getSize();
        int nowPage = memberList.getNumber();
        int totalPage = memberList.getTotalPages();
        int blockNum = 3;

        int startPage =
                (int) ((Math.floor(nowPage / blockNum) * blockNum) + 1 <= totalPage
                        ? (Math.floor(nowPage / blockNum) * blockNum) + 1 : totalPage);

        int endPage =
                (startPage + blockNum - 1 < totalPage ? startPage + blockNum - 1 : totalPage);


        for (int i = startPage; i <= endPage; i++) {
            System.out.println(i + " , ");
        }

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
//        model.addAttribute("postVo", postList);


        model.addAttribute("companyList", memberList);


        return "company/companyList";
    }

    @GetMapping("/staffList")
    public String staffList(@PageableDefault(page = 0, size = 5, sort = "id",
            direction = Sort.Direction.DESC) Pageable pageable,
                            @RequestParam(value = "subject", required = false) String subject,
                            @RequestParam(value = "search", required = false) String search,
                            Model model) {

        Page<MemberDto> memberList = memberService.pageStaffList(pageable, subject, search);


        Long totalCount = memberList.getTotalElements();
        int pagesize = memberList.getSize();
        int nowPage = memberList.getNumber();
        int totalPage = memberList.getTotalPages();
        int blockNum = 3;

        int startPage =
                (int) ((Math.floor(nowPage / blockNum) * blockNum) + 1 <= totalPage
                        ? (Math.floor(nowPage / blockNum) * blockNum) + 1 : totalPage);

        int endPage =
                (startPage + blockNum - 1 < totalPage ? startPage + blockNum - 1 : totalPage);


        for (int i = startPage; i <= endPage; i++) {
            System.out.println(i + " , ");
        }

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
//        model.addAttribute("postVo", postList);


        model.addAttribute("staffList", memberList);


        return "member/staffList";
    }


}





