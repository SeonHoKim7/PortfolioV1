package com.example.PortfolioV1.user.dto;

import lombok.*;

//@Data
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {

    private String userid;
    private String password;
    private String username;

    @Override
    public String toString() {
        return "MemberDto{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
