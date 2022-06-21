package com.klimashin.celestial.body.dto;

import com.klimashin.celestial.body.entity.Body;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BodyRequestDto extends Body {

    String name;
    Double radius;
    Double mass;
    Double gravParameter;
    Double gravRadius;
}
