package com.anhnguyen.githubclient.exception;

/**
 * Exception throw by the application when a User search can't return a valid result.
 */
public class EmailNotExistException extends RuntimeException {

    public EmailNotExistException() {
        super();
    }

    public EmailNotExistException(final String message) {
        super(message);
    }

    public EmailNotExistException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public EmailNotExistException(final Throwable cause) {
        super(cause);
    }
}
