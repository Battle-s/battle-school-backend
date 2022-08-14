package org.battles.battles.exception.exception;

public class CEmailExistedException extends RuntimeException {

    public CEmailExistedException(String msg, Throwable t) {
        super(msg, t);
    }

    public CEmailExistedException(String msg) {
        super(msg);
    }

    public CEmailExistedException() {
        super();
    }

}
