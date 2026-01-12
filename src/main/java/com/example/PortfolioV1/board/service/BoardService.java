package com.example.PortfolioV1.board.service;

import com.example.PortfolioV1.board.dto.BoardRequestDto;
import com.example.PortfolioV1.board.dto.BoardResponseDto;
import com.example.PortfolioV1.board.entity.Board;
import com.example.PortfolioV1.board.model.BoardMapper;
import com.example.PortfolioV1.board.repository.BoardRepository;
import com.example.PortfolioV1.exception.CustomException;
import com.example.PortfolioV1.exception.ErrorCode;
import com.example.PortfolioV1.paging.Pagination;
import com.example.PortfolioV1.paging.PagingParams;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    /*
     * 게시글 생성
     */
    @Transactional
    public Long save(final BoardRequestDto boardDto, final String writer) {

        Board board = boardDto.toEntity(writer);
        boardRepository.save(board);
        return board.getId();
    }

    /*
     * 게시글 리스트 조회
     */
    public Map<String, Object> findAll(PagingParams params) {

        // 기존 JPA 처리 방식
//        Sort sort = Sort.by(Sort.Direction.DESC, "id", "createdDate");
//        List<Board> list = boardRepository.findAll(sort);
//
////        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
//
//        ArrayList<BoardResponseDto> result = new ArrayList<>();
//
//        for (Board board : list) {
//            if (board.getDeleteYn() == 'N') {
//                result.add(new BoardResponseDto(board));
//            }
//        }
//
//        return result;
        int count = boardMapper.count(params);

        if (count < 1) {
            return Collections.emptyMap();
        }

        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        List<BoardResponseDto> list = boardMapper.findAll(params);

        Map<String, Object> response = new HashMap<>();

        response.put("params", params);
        response.put("list", list);

        return response;
    }

    /*
     * 게시글 수정
     * (중요학습. 영속성 컨텍스트의 개념)
     */
    @Transactional
    public Long update(final Long id, final BoardRequestDto boardRequestDto, final String currentUser) {

        Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));

        if (!board.getWriter().equals(currentUser)) {
            throw new CustomException(ErrorCode.METHOD_NOT_ALLOWED);
        }

        board.update(boardRequestDto.getTitle(), boardRequestDto.getContent());

        return board.getId();
    }

    /*
     * 게시글 삭제
     */
    @Transactional
    public Long delete(final Long id, final String currentUsername) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));

        if (!board.getWriter().equals(currentUsername)) {
            throw new CustomException(ErrorCode.METHOD_NOT_ALLOWED);
        }

        board.delete();

        return id;
    }

    /*
     * 게시글 조회
     */
    @Transactional
    public BoardResponseDto findById(final Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));

        if (board.getDeleteYn() == 'Y') {
            throw new CustomException(ErrorCode.POSTS_ALREADY_DELETED);
        }

        return new BoardResponseDto(board);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void increaseViewCount(final Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));

        board.increaseViewCount();
    }
}
