package com.example.PortfolioV1.user.repository;

import com.example.PortfolioV1.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// @Repository -> JpaRepository 인터페이스를 상속받으면 @Repository를 제공
// 따라서 중복 사용X
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserid(String userid);
}
