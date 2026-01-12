package com.example.PortfolioV1.comment.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequest {

    private Long id; // 댓글 번호(PK)
    private Long boardId; // 게시글 번호(FK)
    private String commentContent; // 댓글 내용
    private String writer; // 댓글 작성자

    @Builder
    public CommentRequest(Long id, Long boardId, String commentContent, String writer) {
        this.id = id;
        this.boardId = boardId;
        this.commentContent = commentContent;
        this.writer = writer;
    }
}
