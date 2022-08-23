package org.battles.battles.exception.exception;

public class CDateTimeValidException extends RuntimeException {

    public CDateTimeValidException(String msg, Throwable t) {
        super(msg, t);
    }

    public CDateTimeValidException(String msg) {
        super(msg);
    }

    public CDateTimeValidException() {
        super();
    }

}
