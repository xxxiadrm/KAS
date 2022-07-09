package com.example.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 내부 정의 상태 코드 값
 */
@Getter
@AllArgsConstructor
public enum KasStatus {
    SUCCESS("000000", "success"),
    FAIL("999999", "fail"),

    /**
     * 상품 상태 코드
     */
    PRI2001("RPE3001", "파라미터 오류"),
    PRI2002("PRI2002", "필수 요청 값 누락"),
    PRE2001("PRE2001", "서버 내부 오류"),
    PRE2002("PRE2002", "Json Mapping 오류"),
    PRE2003("PRE2003", "Json Processing 오류 ");

    private final String code;
    private final String message;
}