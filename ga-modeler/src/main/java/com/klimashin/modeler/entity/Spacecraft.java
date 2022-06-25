package com.klimashin.modeler.entity;

import com.klimashin.math.entity.abstraction.Point;
import com.klimashin.math.entity.abstraction.Vector;

import com.klimashin.math.operation.VectorOperation;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Spacecraft implements MaterialPoint {

    double startMass;
    double currentMass;
    Engine engine;
    int engineCount;
    double spacecraftThrust;

    Point currentPosition;
    Vector currentSpeed;
    Vector currentAcceleration;
    Vector currentForce;

    public Spacecraft calculateMoving(Vector currentForce, long deltaTime) {
        this.currentForce = currentForce;
        currentAcceleration = currentForce.div(currentMass);
        currentSpeed.change(currentAcceleration.mult(deltaTime));
        currentPosition.change(currentSpeed.mult(deltaTime));
        return this;
    }
}
