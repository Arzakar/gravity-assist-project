package com.klimashin.celestial.body.mapper;

import com.klimashin.celestial.body.dto.BodyDto;
import com.klimashin.celestial.body.entity.Body;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BodyMapper {

    BodyDto toDto(Body body);
    Body toEntity(BodyDto bodyDto);
}
