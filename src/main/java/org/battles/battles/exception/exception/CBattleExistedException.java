package org.battles.battles.exception.exception;

public class CBattleExistedException extends RuntimeException {

    public CBattleExistedException(String msg, Throwable t) {
        super(msg, t);
    }

    public CBattleExistedException(String msg) {
        super(msg);
    }

    public CBattleExistedException() {
        super();
    }

}
