package org.battles.battles.exception.exception;

public class CPostNotFoundException extends RuntimeException {

    public CPostNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CPostNotFoundException(String msg) {
        super(msg);
    }

    public CPostNotFoundException() {
        super();
    }
}
