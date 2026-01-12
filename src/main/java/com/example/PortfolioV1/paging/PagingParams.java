package com.example.PortfolioV1.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingParams {

    private int page; // 현재 페이지
    private int boardPerPage; // 페이지당 출력할 게시글 수
    private int pageSize; // 하단에 보여지는 페이지 개수(1~10페이지)
    private String searchWord; // 검색어
    private String searchType; // 검색할 유형(제목, 내용, 작성자 등)
    private Pagination pagination;
}
