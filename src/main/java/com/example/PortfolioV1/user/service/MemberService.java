package com.example.PortfolioV1.user.service;

import com.example.PortfolioV1.user.dto.MemberDto;
import com.example.PortfolioV1.user.entity.Member;
import com.example.PortfolioV1.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    /**
     * 회원가입 (비밀번호 암호화 및 JpaRepository 상속을 통한 DB 저장)
     *
     * @param memberDto
     * @return Member
     */

    @Transactional
    public Member joinMember(MemberDto memberDto) {

        Optional<Member> checkedMember = memberRepository.findByUserid(memberDto.getUserid());
        if (memberRepository.findByUserid(memberDto.getUserid()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        Member member = Member.builder()
                .userid(memberDto.getUserid())
                // 비밀번호 암호화
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .username(memberDto.getUsername())
                .build();

        return memberRepository.save(member);
    }

    public Member findByUserid(String userid) {
//        Member member = memberRepository.findByUserid(userid);

        return memberRepository.findByUserid(userid).
                orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));
    }
}

