package com.example.PortfolioV1.comment.service;

import com.example.PortfolioV1.comment.dto.CommentSearchDto;
import com.example.PortfolioV1.comment.model.CommentMapper;
import com.example.PortfolioV1.comment.dto.CommentRequest;
import com.example.PortfolioV1.comment.dto.CommentResponse;
import com.example.PortfolioV1.paging.Pagination;
import com.example.PortfolioV1.paging.PagingParams;
import com.example.PortfolioV1.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;

    /**
     * 댓글 저장
     *
     * @param params - 댓글 정보
     * @return Generated PK
     */
    @Transactional
    public Long saveComment(final CommentRequest params, final String writer) {

        CommentRequest commentToSave = CommentRequest.builder()
                .boardId(params.getBoardId())
                .commentContent(params.getCommentContent())
                .writer(writer)
                .build();

        commentMapper.save(commentToSave);

        return commentToSave.getId();
    }

    /**
     * 댓글 상세정보
     *
     * @param id - PK
     * @return 댓글 상세정보
     */
    public CommentResponse findCommentById(final Long id) {
        return commentMapper.findById(id);
    }

    /**
     * 댓글 수정
     *
     * @param params - 댓글 정보
     * @return PK
     */
    @Transactional
    public Long updateComment(final CommentRequest params) {
        commentMapper.update(params);
        return params.getId();
    }

    /**
     * 댓글 삭제
     *
     * @param id - PK
     * @return PK
     */
    @Transactional
    public Long deleteComment(final Long id) {
        commentMapper.deleteById(id);
        return id;
    }

    /**
     * 댓글 리스트 조회
     *
     * @param -> search conditions
     * @return 특정 게시글에 대해 등록된 댓글 리스트
     */
    public PagingResponse<CommentResponse> findAllComment(final CommentSearchDto params) {

        int count = commentMapper.count(params);

        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        Pagination pagination = new Pagination(count, params);
        List<CommentResponse> list = commentMapper.findAll(params);

        return new PagingResponse<>(list, pagination);
    }
}
