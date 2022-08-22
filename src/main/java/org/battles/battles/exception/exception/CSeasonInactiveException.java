package org.battles.battles.exception.exception;

public class CSeasonInactiveException extends RuntimeException {

    public CSeasonInactiveException(String msg, Throwable t) {
        super(msg, t);
    }

    public CSeasonInactiveException(String msg) {
        super(msg);
    }

    public CSeasonInactiveException() {
        super();
    }

}
