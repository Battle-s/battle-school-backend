package org.battles.battles.response;

import lombok.Getter;

@Getter
public enum CommonResultType {
    SUCCESS(0, "성공"),
    FAIL(-1, "실패");

    int code;
    String msg;

    CommonResultType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
