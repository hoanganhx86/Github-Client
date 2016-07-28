package com.anhnguyen.githubclient.exception;

/**
 * Exception throw by the application when a User search can't return a valid result.
 */
public class ServerException extends RuntimeException {

    public ServerException() {
        super();
    }

    public ServerException(final String message) {
        super(message);
    }

    public ServerException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ServerException(final Throwable cause) {
        super(cause);
    }
}
