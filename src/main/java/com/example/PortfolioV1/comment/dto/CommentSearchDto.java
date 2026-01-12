package com.example.PortfolioV1.comment.dto;

import com.example.PortfolioV1.paging.PagingParams;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentSearchDto extends PagingParams {

    private Long boardId; // FK (게시글 번호)
}
