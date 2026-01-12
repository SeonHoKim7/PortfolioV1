package com.example.PortfolioV1.board.controller;

import com.example.PortfolioV1.board.dto.BoardRequestDto;
import com.example.PortfolioV1.board.dto.BoardResponseDto;
import com.example.PortfolioV1.board.service.BoardService;
import com.example.PortfolioV1.exception.CustomException;
import com.example.PortfolioV1.exception.ErrorCode;
import com.example.PortfolioV1.paging.PagingParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    /*
     * 게시글 리스트 조회
     */
    @GetMapping("/board")
    public Map<String, Object> findAll(final PagingParams params) {
        return boardService.findAll(params);
    }

    /*
     * 게시글 생성
     */
    @PostMapping("/board")
    public ResponseEntity<Long> save(@RequestBody final BoardRequestDto boardRequestDto,
                                     @AuthenticationPrincipal UserDetails userDetails) {

        String writer = userDetails.getUsername();

        Long id = boardService.save(boardRequestDto, writer);

        return ResponseEntity.ok(id);
    }

    /*
     * 게시글 수정
     */
    @PatchMapping("/board/{id}")
    public ResponseEntity<Long> update(@PathVariable("id") final Long id,
                                       @RequestBody final BoardRequestDto boardRequestDto,
                                       @AuthenticationPrincipal UserDetails userDetails) {

        String currentUser = userDetails.getUsername();
        Long updateWriter = boardService.update(id, boardRequestDto, currentUser);

        return ResponseEntity.ok(updateWriter);
    }

    /*
     * 게시글 삭제
     */
    @DeleteMapping("/board/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") final Long id,
                                       @AuthenticationPrincipal UserDetails userDetails) {
        String currentUser = userDetails.getUsername();
        Long deleteWriter = boardService.delete(id, currentUser);

        return ResponseEntity.ok(deleteWriter);
    }

    /*
     * 게시글 조회
     */
    @GetMapping("/board/{id}")
    public ResponseEntity<BoardResponseDto> findById(@PathVariable("id") final Long id) {

        BoardResponseDto dto = boardService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/board/{id}/viewCount")
    public ResponseEntity<Void> increaseViewCount(@PathVariable("id") final Long id) {

        boardService.increaseViewCount(id);
        return ResponseEntity.ok().build();
    }
}
