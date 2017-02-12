/**
 *
 */
package com.github.melpis.member.exception;

/**
 * @author bitacademy
 */
public class MemberUserIdExistException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -8970682188530740979L;

    /**
     *
     */
    public MemberUserIdExistException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public MemberUserIdExistException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public MemberUserIdExistException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public MemberUserIdExistException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

}
