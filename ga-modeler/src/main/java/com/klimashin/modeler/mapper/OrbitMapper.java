package com.klimashin.modeler.mapper;

import com.klimashin.modeler.entity.Orbit;
import com.klimashin.modeler.entity.data.OrbitData;

import org.mapstruct.Mapper;

@Mapper
public interface OrbitMapper {

    OrbitData toData(Orbit orbit);

}
