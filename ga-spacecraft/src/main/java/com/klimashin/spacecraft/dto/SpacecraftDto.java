package com.klimashin.spacecraft.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpacecraftDto {

    String name;
    float mass;
    EngineDto engineDto;
    int engineCount;

}
