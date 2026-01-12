package com.example.PortfolioV1.paging;

import lombok.Getter;

@Getter
public class Pagination {

    private int totalBoardCount; // 전체 게시글 수
    private int totalPageCount; // 전체 페이지 수
    private int startPage; // 첫 페이지 번호
    private int endPage; // 마지막 페이지 번호
    private int limitStart; // LIMIT 시작 위치
    private boolean existPrevPage; // 이전 페이지 존재 여부
    private boolean existNextPage; // 다음 페이지 존재 여부

    public Pagination(int totalBoardCount, PagingParams params) {

        // 게시글이 존재해야 페이징 처리
        if (totalBoardCount > 0) {
            this.totalBoardCount = totalBoardCount;
            calculation(params);
            params.setPagination(this);
        }
    }

    private void calculation(PagingParams params) {

        int boardPerPage = params.getBoardPerPage();
        if (boardPerPage <= 0) {
            throw new IllegalArgumentException("출력할 게시글 수는 1개 이상");
        }

        /*
        전체 페이지 수 계산 로직
        ex) 게시글 1개 -> 전체 게시글 수 ( (1 - 1) / 한 페이지당 출력할 게시글 수(ex 10개) ) + 1
            페이지 1개 ok.
        ex) 게시글 100개 -> 전체 게시글 수 ( (100 - 1) / 한 페이지당 출력할 게시글 수 (ex 10개) ) + 1
            페이지 10개 ok.
        */
        totalPageCount = ((totalBoardCount - 1) / boardPerPage ) + 1;

        // 만약 URL을 통해 3페이지를 요청했지만, 실제로는 2페이지만 존재한다면 최대 페이지인 2페이지를 대신해서 보여주기 위함
        if (params.getPage() > totalPageCount) {
            params.setPage(totalPageCount);
        }

        // 1~10페이지의 경우 시작 페이지를 1페이지로, 11~20페이지의 경우 시작 페이지를 11페이지로
        startPage = ((params.getPage() - 1) / params.getPageSize()) * params.getPageSize() + 1;

        // 마지막 페이지는 10 단위로(10, 20, 30...)
        endPage = startPage + params.getPageSize() - 1;

        // 만약 URL을 통해 24페이지를 요청했지만, 전 페이지는 20페이지 뿐이라면 20페이지를 대신 출력하기 위함
        if (endPage > totalPageCount) {
            endPage = totalPageCount;
        }

        // 만약 4페이지라면 30번 게시글부터 출력하기 위함
        limitStart = (params.getPage() - 1) * params.getBoardPerPage();

        // 1페이지가 아니라면 이전 페이지 버튼 활성화
        existPrevPage = startPage != 1;

        // 계산식의 값이 현재 존재하는 전체 게시글 수 보다 작다면 다음 페이지 버튼 활성화
        existNextPage = (endPage * params.getBoardPerPage()) < totalBoardCount;
    }
}
