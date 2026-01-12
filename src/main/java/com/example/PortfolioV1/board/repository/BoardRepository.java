package com.example.PortfolioV1.board.repository;

import com.example.PortfolioV1.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
