package com.klimashin.modeler.entity;

import com.klimashin.math.entity.abstraction.Point;
import com.klimashin.math.entity.abstraction.Vector;

import com.klimashin.modeler.entity.data.CelestialBodyData;
import com.klimashin.modeler.mapper.CelestialBodyMapper;
import com.klimashin.modeler.util.Stored;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.mapstruct.factory.Mappers;

@Data
@Builder
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CelestialBody implements MaterialPoint, Stored<CelestialBodyData> {

    final CelestialBodyMapper mapper = Mappers.getMapper(CelestialBodyMapper.class);

    String name;
    double radius;
    double currentMass;
    double gravParameter;
    double gravRadius;

    Orbit orbit;

    Point currentPosition;
    Vector currentSpeed;
    Vector currentAcceleration;
    Vector currentForce;

    public CelestialBody calculateMoving(long deltaTime) {
        double currentRadius = orbit.getCurrentRadius();
        currentPosition.setX(currentRadius * Math.cos(orbit.getCurrentTrueAnomaly()))
                .setY(currentRadius * Math.cos(orbit.getCurrentTrueAnomaly()))
                .setZ(0);
        return this;
    }

    @Override
    public CelestialBodyData getData() {
        return mapper.toData(this);
    }
}
