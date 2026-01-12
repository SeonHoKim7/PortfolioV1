package com.example.PortfolioV1.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {

    /** 폼로그인 메뉴얼
     *
     * http.formLogin()
     *        .loginPage(“/login.html")   			// 사용자 정의 로그인 페이지
     *        .defaultSuccessUrl("/home)			// 로그인 성공 후 이동 페이지
     *        .failureUrl(＂/login.html?error=true“)	        // 로그인 실패 후 이동 페이지
     *        .usernameParameter("username")			// 아이디 파라미터명 설정
     *        .passwordParameter(“password”)			// 패스워드 파라미터명 설정
     *        .loginProcessingUrl(“/login")			// 로그인 Form Action Url
     *        .successHandler(loginSuccessHandler())		// 로그인 성공 후 핸들러
     *        .failureHandler(loginFailureHandler())		// 로그인 실패 후 핸들러
     */


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrfConfig) -> csrfConfig.disable())
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/member/login", "member/join").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN") // 관리자 페이지 접근 제한
                        .requestMatchers("/board/write").authenticated() // 게시글 작성 페이지 접근 제한
                        .requestMatchers(HttpMethod.POST, "/board/*/comments").authenticated()
                        .anyRequest().permitAll()
                )

                .formLogin(form -> form
                        .loginPage("/member/login")
                        .loginProcessingUrl("/member/login")
                        .usernameParameter("userid")
                        .defaultSuccessUrl("/", false)
                        .failureUrl("/member/login?error")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }

    // 패스워드 암호화로 사용할 bean
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}