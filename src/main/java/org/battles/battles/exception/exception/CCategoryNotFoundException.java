package org.battles.battles.exception.exception;

public class CCategoryNotFoundException extends RuntimeException {

    public CCategoryNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CCategoryNotFoundException(String msg) {
        super(msg);
    }

    public CCategoryNotFoundException() {
        super();
    }

}
