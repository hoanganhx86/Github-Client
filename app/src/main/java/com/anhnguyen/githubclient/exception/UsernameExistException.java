package com.anhnguyen.githubclient.exception;

/**
 * Exception throw by the application when a User search can't return a valid result.
 */
public class UsernameExistException extends RuntimeException {

    public UsernameExistException() {
        super();
    }

    public UsernameExistException(final String message) {
        super(message);
    }

    public UsernameExistException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UsernameExistException(final Throwable cause) {
        super(cause);
    }
}
