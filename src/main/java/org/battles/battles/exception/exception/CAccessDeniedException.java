package org.battles.battles.exception.exception;

public class CAccessDeniedException extends RuntimeException {

    public CAccessDeniedException(String msg, Throwable t) {
        super(msg, t);
    }

    public CAccessDeniedException(String msg) {
        super(msg);
    }

    public CAccessDeniedException() {
        super();
    }
}
