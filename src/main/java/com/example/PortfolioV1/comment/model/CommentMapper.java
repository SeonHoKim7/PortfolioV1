package com.example.PortfolioV1.comment.model;

import com.example.PortfolioV1.comment.dto.CommentRequest;
import com.example.PortfolioV1.comment.dto.CommentResponse;
import com.example.PortfolioV1.comment.dto.CommentSearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    /**
     * 댓글 저장
     *
     * @param params -> 댓글 정보
     */
    void save(CommentRequest params);

    /**
     * 댓글 상세정보
     *
     * @param id -> PK
     * @return 댓글 상세정보
     */
    CommentResponse findById(Long id);

    /**
     * 댓글 수정
     *
     * @param params -> 댓글 정보
     */
    void update(CommentRequest params);

    /**
     * 댓글 삭제
     *
     * @param id -> PK
     */
    void deleteById(Long id);

    /**
     * 댓글 리스트
     *
     * @param -> search conditions
     * @return 댓글 리스트
     */
    List<CommentResponse> findAll(CommentSearchDto params);

    /**
     * 댓글 수
     *
     * @param -> search conditions
     * @return 댓글 수
     */
    int count(CommentSearchDto params);
}
