package org.spring.dev.company.controller.board;

import lombok.RequiredArgsConstructor;
import org.spring.dev.company.dto.board.ReplyDto;
import org.spring.dev.company.service.board.BoardService;
import org.spring.dev.company.service.board.ReplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final BoardService boardService;
    private final ReplyService replyService;

    @PostMapping("/write")
    public @ResponseBody ReplyDto write(@ModelAttribute ReplyDto replyDto)  {
        return replyService.write(replyDto);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody ReplyDto replyDto)  {
        replyService.update(id, replyDto);
        return ResponseEntity.ok("success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReply(@PathVariable Long id) {
        replyService.replyDelete(id);
        return ResponseEntity.ok("success");
    }


}
