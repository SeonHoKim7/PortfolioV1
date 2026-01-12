package com.example.PortfolioV1.comment.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private Long id; // 댓글 번호(PK)
    private Long boardId; // 게시글 번호(FK)
    private String commentContent; // 댓글 내용
    private String writer; // 댓글 작성자
    private boolean deleteYn; // 댓글 삭제여부
    private LocalDateTime createdDate; // 댓글 생성일(작성일)
    private LocalDateTime modifiedDate; // 댓글 수정일
}
