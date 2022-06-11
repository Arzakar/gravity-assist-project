package com.klimashin.celestial.body.exception;

public class BodyException extends RuntimeException {

    protected BodyException(String message) {
        super(message);
    }

    protected BodyException(String message, Throwable cause) {
        super(message, cause);
    }

    public static class NotFoundBodyException extends BodyException {
        public NotFoundBodyException(String message) {
            super(message);
        }
    }

    public static class BadRequestBodyException extends BodyException {
        public BadRequestBodyException(String message) {
            super(message);
        }

        public BadRequestBodyException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}