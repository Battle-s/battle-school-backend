package org.battles.battles.exception.exception;

public class CNicknameExistedException extends RuntimeException{
    public CNicknameExistedException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNicknameExistedException(String msg) {
        super(msg);
    }

    public CNicknameExistedException() {
        super();
    }
}
