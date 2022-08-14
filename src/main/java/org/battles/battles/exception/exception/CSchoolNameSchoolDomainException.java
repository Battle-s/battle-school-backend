package org.battles.battles.exception.exception;

public class CSchoolNameSchoolDomainException extends RuntimeException{
    public CSchoolNameSchoolDomainException(String msg, Throwable t) {
        super(msg, t);
    }

    public CSchoolNameSchoolDomainException(String msg) {
        super(msg);
    }

    public CSchoolNameSchoolDomainException() {
        super();
    }
}
