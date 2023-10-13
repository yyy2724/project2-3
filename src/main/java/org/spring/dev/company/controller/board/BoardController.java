package org.spring.dev.company.controller.board;

import lombok.RequiredArgsConstructor;
import org.spring.dev.company.config.MyUserDetails;
import org.spring.dev.company.dto.board.BoardDto;
import org.spring.dev.company.dto.board.ReplyDto;
import org.spring.dev.company.entity.board.BoardEntity;
import org.spring.dev.company.entity.util.BoardType;
import org.spring.dev.company.repository.board.BoardRepository;
import org.spring.dev.company.service.board.BoardService;
import org.spring.dev.company.service.board.ReplyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@SessionAttributes("searchHistory")
public class BoardController {

    private final BoardService boardService;
    private final ReplyService replyService;
    private final BoardRepository boardRepository;

    @GetMapping("/write")
    public String write(BoardDto boardDto){
        return "/board/write";
    }

    @PostMapping("/write")
    public String writePost(@AuthenticationPrincipal MyUserDetails myUserDetails, BoardDto boardDto, @RequestParam("file") MultipartFile file ) throws IOException {
        boardDto.setBoardFile(file);
//        String email = myUserDetails.getUsername();
        boardService.boardWrite(boardDto);
        return "redirect:/board/list";
    }

    @ModelAttribute("searchHistory") // 검색 이력을 세션에 저장하기 위한 모델 어트리뷰트
    public List<String> initializeSearchHistory() {
        return new ArrayList<>();
    }

    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(name = "boardType", required = false, defaultValue = "GENERAL") BoardType boardType,
                       @RequestParam(value = "subject", required = false) String subject,
                       @RequestParam(value = "search", required = false) String search,
                       @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                       @ModelAttribute("searchHistory") List<String> searchHistory) {

        Page<BoardEntity> boardList;

        if (search != null && !search.isEmpty()) {
            searchHistory.add(search);
        }

        if (search != null && !search.isEmpty()) {
            if ("title".equals(subject)) {
                boardList = boardRepository.findByBoardTypeAndTitleContaining(boardType, search, pageable);
            } else if ("content".equals(subject)) {
                boardList = boardRepository.findByBoardTypeAndContentContaining(boardType, search, pageable);
            } else if ("writer".equals(subject)) {
                boardList = boardRepository.findByBoardTypeAndWriterContaining(boardType, search, pageable);
            } else {
                boardList = boardRepository.findByBoardTypeAndTitleContaining(boardType, search, pageable);
            }
        } else {
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
        model.addAttribute("search", search);
        model.addAttribute("key", "boardList");

        return "board/list";
    }

    @GetMapping("/detail/{id}")
    private String detail(@PathVariable("id") Long id, Model model){
        BoardDto boardDto = boardService.detail(id);
//        List<ReplyDto> replyDtoList = replyService.list(boardDto.getId());
//        Collections.reverse(replyDtoList);
        model.addAttribute("board", boardDto);
//        model.addAttribute("replyList", replyDtoList);

        return "board/detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model){
        BoardDto board = boardService.update(id);
        model.addAttribute("board", board);
        return "/board/update";
    }

    @PostMapping("/update")
    public String updatePost(BoardDto boardDto) throws IOException{
        boardService.updateOk(boardDto);
        return "redirect:/board/detail/"+boardDto.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        boardService.delete(id);
        return "redirect:/board/list";
    }





}
