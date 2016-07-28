package com.anhnguyen.githubclient.exception;

/**
 * Exception throw by the application when a User search can't return a valid result.
 */
public class AuthenErrorException extends RuntimeException {

    public AuthenErrorException() {
        super();
    }

    public AuthenErrorException(final String message) {
        super(message);
    }

    public AuthenErrorException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AuthenErrorException(final Throwable cause) {
        super(cause);
    }
}
