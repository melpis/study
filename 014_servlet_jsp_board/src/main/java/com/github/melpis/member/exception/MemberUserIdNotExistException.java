/**
 *
 */
package com.github.melpis.member.exception;

/**
 * @author bitacademy
 */
public class MemberUserIdNotExistException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 3575202229591803121L;

    /**
     *
     */
    public MemberUserIdNotExistException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public MemberUserIdNotExistException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public MemberUserIdNotExistException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public MemberUserIdNotExistException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

}
