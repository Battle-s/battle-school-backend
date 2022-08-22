package org.battles.battles.exception.exception;

public class CSeasonNotFoundException extends RuntimeException {

    public CSeasonNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CSeasonNotFoundException(String msg) {
        super(msg);
    }

    public CSeasonNotFoundException() {
        super();
    }

}
