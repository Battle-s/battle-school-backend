package org.battles.battles.exception.exception;

public class CBattleNotFoundException extends RuntimeException{
    public CBattleNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CBattleNotFoundException(String msg) {
        super(msg);
    }

    public CBattleNotFoundException() {
        super();
    }

}
