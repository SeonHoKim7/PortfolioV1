package com.example.PortfolioV1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /*
    400(BAD_REQUEST) - 클라이언트의 잘못된 요청
     */
    BAD_REQUEST (HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),

    /*
    404(NOT_FOUND) : 리소스를 찾을 수 없음
     */
    POSTS_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 게시글을 찾을 수 없습니다."),

    /*
    409(CONFLICT) : 리소스 상태와의 충돌 에러
     */
    POSTS_ALREADY_DELETED(HttpStatus.CONFLICT, "삭제된 게시글입니다."),

    /*
    405(METHOD_NOT_ALLOWED : 허용되지 않은 Request Method 호출
     */
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메서드입니다."),

    /*
    500(INTERNAL_SERVER_ERROR : 서버 에러
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버의 내부 오류가 발생했습니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
