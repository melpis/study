/**
 *
 */
package com.github.melpis.member.exception;

/**
 * @author bitacademy
 */
public class MemberLoginRequiredException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -4253978333174455597L;

    /**
     *
     */
    public MemberLoginRequiredException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public MemberLoginRequiredException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public MemberLoginRequiredException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public MemberLoginRequiredException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

}
