package org.spring.dev.company.dto.board;

import lombok.*;
import org.spring.dev.company.entity.board.BoardEntity;
import org.spring.dev.company.entity.member.MemberEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;
    private String content;
    private String writer;
    private Long boardId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private BoardEntity boardEntity;
    private MemberEntity memberEntity;

}
