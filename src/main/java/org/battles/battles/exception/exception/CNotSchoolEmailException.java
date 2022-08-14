package org.battles.battles.exception.exception;

public class CNotSchoolEmailException extends RuntimeException{

    public CNotSchoolEmailException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNotSchoolEmailException(String msg) {
        super(msg);
    }

    public CNotSchoolEmailException() {
        super();
    }

}
