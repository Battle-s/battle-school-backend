package org.battles.battles.exception.exception;

public class CSeasonStartValidException extends RuntimeException {

    public CSeasonStartValidException(String msg, Throwable t) {
        super(msg, t);
    }

    public CSeasonStartValidException(String msg) {
        super(msg);
    }

    public CSeasonStartValidException() {
        super();
    }

}
