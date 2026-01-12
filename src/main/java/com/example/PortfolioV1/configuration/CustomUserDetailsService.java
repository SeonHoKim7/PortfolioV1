package com.example.PortfolioV1.configuration;

import com.example.PortfolioV1.user.entity.Member;
import com.example.PortfolioV1.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {

        Optional<Member> optionalMember = memberRepository.findByUserid(userid);

        if (optionalMember.isEmpty()) {
            throw new UsernameNotFoundException("아이디 혹은 비밀번호가 틀립니다.");
        }

        Member member = optionalMember.get();

        return User.builder()
                .username(member.getUserid())
                .password(member.getPassword())
                .roles("USER")
                .build();
    }
}
