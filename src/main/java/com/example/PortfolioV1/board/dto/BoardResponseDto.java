package com.example.PortfolioV1.board.dto;

import com.example.PortfolioV1.board.entity.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponseDto {

    private Long id; // 기본키(PK)
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private String writer; // 게시글 작성자
    private int viewCount; // 게시글 조회수
    private char deleteYn; // 게시글 삭제여부
    private LocalDateTime createdDate; // 게시글 생성일
    private LocalDateTime modifiedDate; // 게시글 수정일

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getWriter();
        this.viewCount = board.getViewCount();
        this.deleteYn = board.getDeleteYn();
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
    }
}
