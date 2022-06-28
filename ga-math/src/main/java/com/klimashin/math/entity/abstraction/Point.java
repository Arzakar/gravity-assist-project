package com.klimashin.math.entity.abstraction;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PROTECTED)
public class Point {

    double x;
    double y;
    double z;

    public Point change(double deltaX, double deltaY, double deltaZ) {
        return this.changeX(deltaX).changeY(deltaY).changeZ(deltaZ);
    }

    public Point changeX(double deltaX) {
        this.x += deltaX;
        return this;
    }

    public Point changeY(double deltaY) {
        this.y += deltaY;
        return this;
    }

    public Point changeZ(double deltaZ) {
        this.z += deltaZ;
        return this;
    }
}
