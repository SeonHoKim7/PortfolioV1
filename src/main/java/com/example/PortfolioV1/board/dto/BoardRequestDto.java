package com.example.PortfolioV1.board.dto;

import com.example.PortfolioV1.board.entity.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {

    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private String writer; // 게시글 작성자
    private char deleteYn; // 게시글 삭제여부

    public Board toEntity(String writer) {
        return Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .viewCount(0)
                .deleteYn(deleteYn)
                .build();
    }
}
