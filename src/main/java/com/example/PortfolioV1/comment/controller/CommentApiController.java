package com.example.PortfolioV1.comment.controller;

import com.example.PortfolioV1.comment.dto.CommentRequest;
import com.example.PortfolioV1.comment.dto.CommentResponse;
import com.example.PortfolioV1.comment.dto.CommentSearchDto;
import com.example.PortfolioV1.comment.service.CommentService;
import com.example.PortfolioV1.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/board/{boardId}/comments")
    public ResponseEntity<CommentResponse> saveComment(@PathVariable("boardId") final Long boardId,
                                                       @RequestBody final CommentRequest params,
                                                       @AuthenticationPrincipal UserDetails userDetails) {
        String writer = userDetails.getUsername();

        Long id = commentService.saveComment(params, writer);

        CommentResponse comment = commentService.findCommentById(id);

        return ResponseEntity.ok(comment);
    }

    // 댓글 리스트 조회
    @GetMapping(
            value = "/board/{boardId}/comments",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PagingResponse<CommentResponse> findAllComment(@PathVariable("boardId") final Long boardId, final CommentSearchDto params) {
        return commentService.findAllComment(params);
    }

    // 댓글 상세정보 조회(수정 시 기존 댓글을 보여주기 위해)
    @GetMapping("/board/{boardId}/comments/{id}")
    public CommentResponse findCommentById(@PathVariable("boardId") final Long boardId,
                                           @PathVariable("id") final Long id) {
        return commentService.findCommentById(id);
    }

    // 댓글 수정
    @PatchMapping("/board/{boardId}/comments/{id}")
    public CommentResponse updateComment(@PathVariable("boardId") final Long boardId,
                                         @PathVariable("id") final Long id,
                                         @RequestBody final CommentRequest params) {
        commentService.updateComment(params);

        return commentService.findCommentById(id);
    }

    @DeleteMapping("/board/{boardId}/comments/{id}")
    public Long deleteComment(@PathVariable("boardId") final Long boardId,
                              @PathVariable("id") final Long id) {
        return commentService.deleteComment(id);
    }
}
