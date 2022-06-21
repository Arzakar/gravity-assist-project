package com.klimashin.celestial.body.exception;

public class BodyException extends RuntimeException {

    protected BodyException(String message) {
        super(message);
    }

    public static class NotFoundBodyException extends BodyException {
        public NotFoundBodyException(String name) {
            super(String.format("Небесное тело с именем '%s' не найдено", name));
        }
    }

    public static class BadRequestBodyException extends BodyException {
        public BadRequestBodyException(String message) {
            super(message);
        }

        public BadRequestBodyException(BadRequestReason badRequestReason, String value) {
            super(switch (badRequestReason) {
                case BODY_ALREADY_EXIST -> String.format("Небесное тело с именем '%s' уже существует", value);
            });
        }
    }

    public enum BadRequestReason {
        BODY_ALREADY_EXIST
    }
}
