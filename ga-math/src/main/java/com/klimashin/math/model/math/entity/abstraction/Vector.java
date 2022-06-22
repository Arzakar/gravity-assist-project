package com.klimashin.math.model.math.entity.abstraction;

import com.klimashin.math.model.math.entity.Position;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PROTECTED)
@SuppressWarnings("unchecked")
public abstract class Vector<T extends Vector<T>> {

    double x;
    double y;
    double z;

    Point<? extends Point<?>> firstPoint;
    Point<? extends Point<?>> secondPoint;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Point<? extends Point<?>> firstPoint, Point<? extends Point<?>> secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;

        this.x = secondPoint.getX() - firstPoint.getX();
        this.y = secondPoint.getY() - firstPoint.getY();
        this.z = secondPoint.getZ() - firstPoint.getZ();
    }

    /**
     * TODO Добавить проверку, что secondPoint - firstPoint дают заданные x, y, z
     */
    public Vector(double x, double y, double z, Point<? extends Point<?>> firstPoint, Point<? extends Point<?>> secondPoint) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

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

    public T setFirstPoint(Point<? extends Point<?>> firstPoint) {
        this.firstPoint = firstPoint;

        if (Objects.nonNull(secondPoint)) {
            this.x = this.secondPoint.getX() - firstPoint.getX();
            this.y = this.secondPoint.getY() - firstPoint.getY();
            this.z = this.secondPoint.getZ() - firstPoint.getZ();
        }

        return (T) this;
    }

    public T setSecondPoint(Point<? extends Point<?>> secondPoint) {
        this.secondPoint = secondPoint;

        if (Objects.nonNull(firstPoint)) {
            this.x = secondPoint.getX() - this.firstPoint.getX();
            this.y = secondPoint.getY() - this.firstPoint.getY();
            this.z = secondPoint.getZ() - this.firstPoint.getZ();
        }

        return (T) this;
    }

    public T change(double deltaX, double deltaY, double deltaZ) {
        return this.changeX(deltaX).changeY(deltaY).changeZ(deltaZ);
    }

    public T changeX(double deltaX) {
        this.x += deltaX;

        if (Objects.nonNull(this.firstPoint) && Objects.nonNull(this.secondPoint)) {
            this.secondPoint.setX(this.firstPoint.getX() + this.x);
        }

        return (T) this;
    }

    public T changeY(double deltaY) {
        this.y += deltaY;

        if (Objects.nonNull(this.firstPoint) && Objects.nonNull(this.secondPoint)) {
            this.secondPoint.setY(this.firstPoint.getY() + this.y);
        }

        return (T) this;
    }

    public T changeZ(double deltaZ) {
        this.z += deltaZ;

        if (Objects.nonNull(this.firstPoint) && Objects.nonNull(this.secondPoint)) {
            this.secondPoint.setZ(this.firstPoint.getZ() + this.z);
        }

        return (T) this;
    }
}
