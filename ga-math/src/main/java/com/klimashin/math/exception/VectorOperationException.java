package com.klimashin.math.exception;

public class VectorOperationException extends RuntimeException {

    public VectorOperationException(ReflectiveOperationException exception) {
        super(exception.getMessage(), exception.getCause());
    }

    public VectorOperationException(String message) {
        super(message);
    }
}
