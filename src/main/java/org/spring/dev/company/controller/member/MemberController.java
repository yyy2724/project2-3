package org.spring.dev.company.controller.member;

import lombok.RequiredArgsConstructor;
import org.spring.dev.company.config.MyUserDetails;
import org.spring.dev.company.dto.member.MemberDto;
import org.spring.dev.company.service.member.MemberService;
import org.spring.dev.company.service.member.EmailService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@RequestMapping("/member")
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;
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
    public String detailMamber(@PathVariable("id") Long memberId, Model model) {

        MemberDto memberDto = memberService.detailMember(memberId);
        model.addAttribute("memberDto", memberDto);
        return "member/detail";
    }

    @GetMapping("/up/{id}")
    public String updateMember(@PathVariable("id") Long memberId, Model model) {
        MemberDto memberDto = memberService.detailMember(memberId);
        model.addAttribute("memberDto", memberDto);
        return "member/update";
    }

    @GetMapping("/findPw")
    public String findPwPage() {

        return "member/findPw";
    }

//    @GetMapping("/check/findPw")
//    public @ResponseBody Map<String, Boolean> findPw(String email, String name){
//        Map<String, Boolean> json = new HashMap<>();
//        boolean pwFindCheck = memberService.userEmailCheck(email, name);
//
//        System.out.println(pwFindCheck);
//        json.put("check", pwFindCheck);
//        return json;
//    }
//

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
        MemberDto memberDto1 = memberService.updateMember(memberDto, memberId);
        model.addAttribute("memberDto", memberDto1);
        return "member/detail";
    }


    @GetMapping("/disabled/{memberId}")
    public String disabled(@PathVariable("memberId")Long memberId, Model model){
        MemberDto memberDto = memberService.detailMember(memberId);
        model.addAttribute("memberDto", memberDto);
        return "member/disabled";
    }

    @PostMapping("/disabled/{memberId}")
    public String disMember(@PathVariable("memberId") Long memberId) {

        int rs = memberService.disabledMember(memberId);
        if (rs != 0) {
        return "member/detail";
        }
            return "member/m";
    }


    // 멤버리스트 가져오기(매니저만, 모든권한) -> 프리렌서들꺼는 못보게 / 모든 수정 삭제 가능?? 관지자 창에서 보이게 ->
// 메니저제외 직원이 프리렌서들 리스트 보게 / 모든 수정 삭제 불가 그냥 보게만? 관리자 제외 사람들이 보이게
    // 프리렌서는 못봄
//    @GetMapping("/memberList")
//    public String memberList(@PageableDefault(page = 0, size = 10, sort = "id",
//            direction = Sort.Direction.DESC) Pageable pageable,
//                             @RequestParam(value = "subject", required = false) String subject,
//                             @RequestParam(value = "search", required = false) String search,
//                             Model model) {
//
//        Page<MemberDto> memberList = memberService.pageMemberList(pageable, subject, search);
//
//
//        Long totalCount = memberList.getTotalElements();
//        int pagesize = memberList.getSize();
//        int nowPage = memberList.getNumber();
//        int totalPage = memberList.getTotalPages();
//        int blockNum = 3;
//
//        int startPage =
//                (int) ((Math.floor(nowPage / blockNum) * blockNum) + 1 <= totalPage
//                        ? (Math.floor(nowPage / blockNum) * blockNum) + 1 : totalPage);
//
//        int endPage =
//                (startPage + blockNum - 1 < totalPage ? startPage + blockNum - 1 : totalPage);
//
//
//        for (int i = startPage; i <= endPage; i++) {
//            System.out.println(i + " , ");
//        }
//
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
////        model.addAttribute("postVo", postList);
//
//
//        model.addAttribute("memberList", memberList);
////            return "post/postList";
//
//        return "member/memberList";
//
//    }


    // 비밀번호 변경
    @GetMapping("/pwChange/{memberId}")
    public String pwChange(@PathVariable("memberId") Long id, Model model) {
        MemberDto memberDto = memberService.detailMember(id);
        model.addAttribute("memberDto", memberDto);
        return "member/pwChange";
    }

    @PostMapping("/pwChange/{memberId}")
    public String pwChangePost(@ModelAttribute MemberDto memberDto, @PathVariable("memberId") Long memberId, Model model) {
        int rs = memberService.passwordChange(memberDto, memberId);
        if (rs != 1){
            MemberDto memberDto1 = memberService.detailMember(memberId);
            model.addAttribute("memberDto",memberDto1);
            return "member/detail";
        }
        return "redirect:/member/logout";
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
        MemberDto memberDto = memberService.detailMember(memberId);
        
        model.addAttribute("memberDto", memberDto);
        return "freelancer/detail";
    }

    @GetMapping("/freeUp/{memberId}")
    public String freeUp(@PathVariable("memberId") Long memberId, Model model) {
        MemberDto memberDto = memberService.detailMember(memberId);
        model.addAttribute("memberDto", memberDto);
        return "freelancer/update";
    }

    @PostMapping("/freeUpdate/{memberId}")
    public String freeUpdate(@ModelAttribute MemberDto memberDto, @PathVariable("memberId") Long memberId, Model model) {
        MemberDto memberDto1 = memberService.freeUpdate(memberDto, memberId);
        model.addAttribute("memberDto", memberDto1);
        return "freelancer/detail";
    }


    @GetMapping("/companyDetail/{memberId}")
    public String companyDetail(@PathVariable("memberId") Long memberId, Model model) {
        MemberDto memberDto = memberService.companyDetail(memberId);
        model.addAttribute("memberDto", memberDto);
        return "company/detail";
    }

    @GetMapping("/companyUp/{memberId}")
    public String companyUp(@PathVariable("memberId") Long memberId, Model model) {
        MemberDto memberDto = memberService.companyDetail(memberId);
        model.addAttribute("memberDto", memberDto);
        return "company/update";
    }

    @PostMapping("companyUpdate/{memberId}")
    public String companyUpdatePost(@PathVariable("memberId") Long memberId, @ModelAttribute MemberDto memberDto, Model model) {
        MemberDto memberDto1 = memberService.companyUpdate(memberDto, memberId);
        model.addAttribute("memberDto", memberDto1);
        return "company/detail";
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





