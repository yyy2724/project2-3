package org.spring.dev.company.controller;

import lombok.RequiredArgsConstructor;
import org.spring.dev.company.dto.workTime.WorkTimeDto;
import org.spring.dev.company.entity.board.BoardEntity;
import org.spring.dev.company.entity.util.BaseEntity;
import org.spring.dev.company.entity.util.BoardType;
import org.spring.dev.company.repository.board.BoardRepository;
import org.spring.dev.company.service.workTime.WorkTimeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
@SessionAttributes("searchHistory")
public class indexController {

    private final BoardRepository boardRepository;
    private final WorkTimeService workTimeService;

//    main 페이지

    /*
    추후 멤버정보 가져오는거 수정필요
     */
    @GetMapping({"/","/index"})
    public String index(Model model,
                        @RequestParam(name = "boardType", required = false, defaultValue = "GENERAL") BoardType boardType,
                        @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                        @RequestParam(value = "search", required = false) String search,
                        @ModelAttribute("searchHistory") List<String> searchHistory,
//                        @PathVariable("memberId") Long memberId,
                        @RequestParam(value = "workType", required = false) String workType){
//boardlist가져오기
        Page<BoardEntity> boardList;
        if (search != null && !search.isEmpty()) {
            searchHistory.add(search);
        }
        if(search != null && !search.isEmpty()){
            boardList = boardRepository.findByBoardTypeAndTitleContaining(boardType, search, pageable);
        }else{
            boardList = boardRepository.findByBoardType(boardType, pageable);
        }
        Long totalCount = boardList.getTotalElements();
        int pageSize = boardList.getSize();
        int nowPage = boardList.getNumber();
        int totalPage = boardList.getTotalPages();
        int blockNum = 3;
        int startPage = (int)((Math.floor(nowPage/blockNum)) + 1 < totalPage ? (Math.floor(nowPage/blockNum) * blockNum) + 1 : totalPage);
        int endPage = (startPage + blockNum - 1 < totalPage ? startPage + blockNum - 1 : totalPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boardList", boardList);
        model.addAttribute("boardType", boardType);
        model.addAttribute("key", "boardList");

//        오늘의 근무 가져오기
//        Map<String, Object> map = new HashMap<>();

        // 근무기록 list로 가져오기(반환)
//        List<WorkTimeDto> workTimeList = workTimeService.getWorkTimeWorkList(memberId,workType);


        return "index";
    }
}
