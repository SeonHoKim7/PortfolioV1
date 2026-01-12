package com.example.PortfolioV1.user.controller;

import com.example.PortfolioV1.user.dto.MemberDto;
import com.example.PortfolioV1.user.entity.Member;
import com.example.PortfolioV1.user.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // 회원가입

    @GetMapping("/join")
    public String joinMemberPage() {
        return "member/register";
    }

    @PostMapping("/join")
    public String joinMember(@ModelAttribute MemberDto memberDto, BindingResult bindingResult) {

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

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("member");

        return "redirect:/";
    }
}
