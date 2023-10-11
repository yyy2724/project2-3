package org.spring.dev.company.service.board;

import lombok.RequiredArgsConstructor;
import org.spring.dev.company.dto.board.ReplyDto;
import org.spring.dev.company.entity.board.BoardEntity;
import org.spring.dev.company.entity.board.ReplyEntity;
import org.spring.dev.company.repository.board.BoardRepository;
import org.spring.dev.company.repository.board.ReplyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public ReplyDto write(ReplyDto replyDto) {
        boardRepository.findById(replyDto.getBoardId()).orElseThrow(() -> {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        });

        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(replyDto.getBoardId());

        ReplyDto replyDto1 = ReplyDto.builder()
                .boardEntity(boardEntity)
                .content(replyDto.getContent())
                .writer(replyDto.getWriter())
                .build();

        ReplyEntity replyEntity = ReplyEntity.builder()
                .boardEntity(replyDto1.getBoardEntity())
                .writer(replyDto1.getWriter())
                .content(replyDto1.getContent())
                .boardEntity(replyDto1.getBoardEntity())
                .build();

        Long replyId = replyRepository.save(replyEntity).getId();
        ReplyEntity replyEntity1 = replyRepository.findById(replyId).orElseThrow(() -> {
            throw new IllegalArgumentException("댓글이 존재하지 않습니다.");
        });

        return ReplyDto.builder()
                .id(replyEntity1.getId())
                .content(replyEntity1.getContent())
                .writer(replyEntity1.getWriter())
                .boardId(replyEntity1.getBoardEntity().getId())
                .createTime(replyEntity1.getCreateTime())
                .build();
    }

    @Transactional
    public List<ReplyDto> list(Long id) {
        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        });

        List<ReplyDto> replyDtoList = new ArrayList<>();
        List<ReplyEntity> replyEntityList = replyRepository.findAllByBoardEntity(boardEntity);

        for (ReplyEntity replyEntity : replyEntityList) {
            ReplyDto replyDto = ReplyDto.builder()
                    .id(replyEntity.getId())
                    .writer(replyEntity.getWriter())
                    .content(replyEntity.getContent())
                    .createTime(replyEntity.getCreateTime())
                    .boardId(replyEntity.getBoardEntity().getId())
                    .build();
            replyDtoList.add(replyDto);
        }

        return replyDtoList;

    }


    @Transactional
    public ResponseEntity<ReplyEntity> update(Long id, ReplyDto replyDto) {


        ReplyEntity replyEntity = replyRepository.findById(replyDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        ReplyDto replyDto1 = ReplyDto.builder()
                .id(replyEntity.getId())
                .content(replyEntity.getContent())
                .writer(replyEntity.getWriter())
                .createTime(replyEntity.getCreateTime())
                .updateTime(replyEntity.getUpdateTime())
                .build();

        replyEntity.setContent(replyDto.getContent());
        replyEntity.setWriter(replyDto.getWriter());

        return ResponseEntity.ok(replyRepository.save(replyEntity));
    }

    @Transactional
    public void replyDelete(Long id) {

        ReplyEntity replyEntity = replyRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("댓글이 존재하지 않습니다.");
        });

        replyRepository.delete(replyEntity);
    }
}
