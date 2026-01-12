package com.example.PortfolioV1.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본키(PK)

    @Column(nullable = false)
    private String title; // 게시글 제목
    @Column(nullable = false)
    private String content; // 게시글 내용
    @Column(nullable = false)
    private String writer; // 게시글 작성자
    @Column(nullable = false)
    private int viewCount; // 게시글 조회수
    @Column(nullable = false)
    private char deleteYn; // 게시글 삭제여부
    @Column(nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now(); // 게시글 생성일
    private LocalDateTime modifiedDate; // 게시글 수정일

    @Builder
    public Board(String title, String content, String writer, int viewCount, char deleteYn) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.viewCount = viewCount;
        this.deleteYn = deleteYn;
    }

    /*
    * 게시글 수정
    */
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.modifiedDate = LocalDateTime.now();
    }

    /*
    * (!)조회수 증가와 게시글 삭제 또한 영속성 컨텍스트에 대한 개념
    /*

     /*
     * 조회수 증가
     */
    public void increaseViewCount() {
        this.viewCount++;
    }

    /*
     * 게시글 삭제
     */
    public void delete() {
        this.deleteYn = 'Y';
    }
}
