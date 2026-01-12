package com.example.PortfolioV1.user.controller;

import com.example.PortfolioV1.user.dto.MemberDto;
import com.example.PortfolioV1.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;


    // 회원가입

    @GetMapping("/join")
    public String joinMemberPage() {
        return "member/register";
    }

    @PostMapping("/join")
    public String joinMember(@ModelAttribute MemberDto memberDto) {

        log.info("memberDto: {}", memberDto);

        memberService.joinMember(memberDto);

        // 회원가입 후 Index 페이지로 이동
        return "redirect:/";
    }


    // 로그인

    @GetMapping("/login")
    public String loginPage() {
        return "member/login";
    }

//    @PostMapping("/login")
//    public String login(@RequestParam("userid") String userid,
//                        @RequestParam("password") String password, HttpSession session, Model model) {
//        Member member = memberService.findByUserid(userid);
//        if (member != null && passwordEncoder.matches(password, member.getPassword())) {
//            session.setAttribute("member", member);
//            return "redirect:/";
//        } else {
//            model.addAttribute("error", "아이디 혹은 비밀번호가 틀립니다.");
//        }

//        return "member/login";
//    }

    /* 로그아웃은 Spring Security의 LogoutFilter가 가로채서 처리.
    *  로그아웃, 세션 무효화는 Spring Security 내부에서 처리로 맡김. */
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        session.removeAttribute("member");
//
//        return "redirect:/";
//    }
}
