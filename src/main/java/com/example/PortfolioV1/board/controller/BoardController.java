package com.example.PortfolioV1.board.controller;

import com.example.PortfolioV1.board.dto.BoardResponseDto;
import com.example.PortfolioV1.board.service.BoardService;
import com.example.PortfolioV1.paging.PagingParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    /*
     * 게시글 리스트 페이지
     */
    @GetMapping("/list")
    public String boardList(@ModelAttribute PagingParams params, Model model) {

        Map<String, Object> boardList = boardService.findAll(params);
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    /*
     * 게시글 등록 페이지
     */
    @GetMapping("/write")
    public String boardWrite() {
        return "board/write";
    }

    /*
     * 게시글 조회 페이지
     */
    @GetMapping("/view/{id}")
    public String boardView(@PathVariable("id") final Long id, Model model) {
        BoardResponseDto boardDto = boardService.findById(id);

        model.addAttribute("board", boardDto);

        return "board/view";
    }

    /*
     * 게시글 수정 페이지
     */
    @GetMapping("/write/{id}")
    public String boardUpdate(@PathVariable("id") Long id, Model model) {
        BoardResponseDto boardDto = boardService.findById(id);

        model.addAttribute("id", id);
        model.addAttribute("board", boardDto);

        return "board/write";
    }
}
