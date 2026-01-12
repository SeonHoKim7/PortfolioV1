package com.example.PortfolioV1.board.model;

import com.example.PortfolioV1.board.dto.BoardResponseDto;
import com.example.PortfolioV1.paging.PagingParams;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    /*
     * 게시글 수
     */
    int count(final PagingParams params);

    /*
    게시글 리스트
     */
    List<BoardResponseDto> findAll(final PagingParams params);
}
