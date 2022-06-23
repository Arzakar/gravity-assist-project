package com.klimashin.math.model.math.entity.abstraction;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PROTECTED)
@SuppressWarnings("unchecked")
public abstract class Point<T extends Point<T>> {

    double x;
    double y;
    double z;

    public T setX(double x) {
        this.x = x;
        return (T) this;
    }

    public T setY(double y) {
        this.y = y;
        return (T) this;
    }

    public T setZ(double z) {
        this.z = z;
        return (T) this;
    }

    public T change(double deltaX, double deltaY, double deltaZ) {
        return this.changeX(deltaX).changeY(deltaY).changeZ(deltaZ);
    }

    public T changeX(double deltaX) {
        this.x += deltaX;
        return (T) this;
    }

    public T changeY(double deltaY) {
        this.y += deltaY;
        return (T) this;
    }

    public T changeZ(double deltaZ) {
        this.z += deltaZ;
        return (T) this;
    }
}
