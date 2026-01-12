package com.example.PortfolioV1.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = "id")
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID(PK)

    private String userid; // 유저 아이디
    private String password; // 유저 패스워드

    private String username; // 유저 이름

    @Builder
    public Member(String userid, String password, String username) {
        this.userid = userid;
        this.password = password;
        this.username = username;
    }

}
