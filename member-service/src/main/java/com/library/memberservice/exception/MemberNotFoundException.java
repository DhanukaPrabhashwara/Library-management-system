package com.library.memberservice.exception;

/**
 * Custom exception thrown when a member is not found in the database.
 */
public class MemberNotFoundException extends RuntimeException {

    /**
     * Constructs a MemberNotFoundException with a detail message.
     *
     * @param message the detail message
     */
    public MemberNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a MemberNotFoundException with a detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause
     */
    public MemberNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
