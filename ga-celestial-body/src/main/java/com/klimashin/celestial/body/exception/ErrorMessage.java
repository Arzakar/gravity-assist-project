package com.klimashin.celestial.body.exception;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorMessage {

    @Builder.Default
    LocalDateTime timestamp = LocalDateTime.now();
    int statusCode;
    String message;
    Throwable cause;

}
