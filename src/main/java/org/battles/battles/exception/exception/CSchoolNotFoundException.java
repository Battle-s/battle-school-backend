package org.battles.battles.exception.exception;

public class CSchoolNotFoundException extends RuntimeException{

    public CSchoolNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CSchoolNotFoundException(String msg) {
        super(msg);
    }

    public CSchoolNotFoundException() {
        super();
    }
}
