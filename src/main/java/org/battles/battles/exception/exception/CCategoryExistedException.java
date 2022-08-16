package org.battles.battles.exception.exception;

public class CCategoryExistedException extends RuntimeException {

    public CCategoryExistedException(String msg, Throwable t) {
        super(msg, t);
    }

    public CCategoryExistedException(String msg) {
        super(msg);
    }

    public CCategoryExistedException() {
        super();
    }

}
