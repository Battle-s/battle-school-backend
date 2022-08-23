package org.battles.battles.exception.exception;

public class CCategoryInactiveException extends RuntimeException{
    public CCategoryInactiveException(String msg, Throwable t) {
        super(msg, t);
    }

    public CCategoryInactiveException(String msg) {
        super(msg);
    }

    public CCategoryInactiveException() {
        super();
    }
}
