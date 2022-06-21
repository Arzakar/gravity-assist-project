package com.klimashin.celestial.body.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BodyDto {

    String name;
    Double radius;
    Double mass;
    Double gravParameter;
    Double gravRadius;
}
