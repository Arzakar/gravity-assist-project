package com.klimashin.math.entity.abstraction;

import com.klimashin.math.operation.VectorOperation;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PROTECTED)
public class Vector {

    double x;
    double y;
    double z;

    Point firstPoint;
    Point secondPoint;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Point firstPoint, Point secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;

        this.x = secondPoint.getX() - firstPoint.getX();
        this.y = secondPoint.getY() - firstPoint.getY();
        this.z = secondPoint.getZ() - firstPoint.getZ();
    }

    /**
     * TODO Добавить проверку, что secondPoint - firstPoint дают заданные x, y, z
     */
    public Vector(double x, double y, double z, Point firstPoint, Point secondPoint) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    public Vector setFirstPoint(Point firstPoint) {
        this.firstPoint = firstPoint;

        if (Objects.nonNull(secondPoint)) {
            this.x = this.secondPoint.getX() - firstPoint.getX();
            this.y = this.secondPoint.getY() - firstPoint.getY();
            this.z = this.secondPoint.getZ() - firstPoint.getZ();
        }

        return this;
    }

    public Vector setSecondPoint(Point secondPoint) {
        this.secondPoint = secondPoint;

        if (Objects.nonNull(firstPoint)) {
            this.x = secondPoint.getX() - this.firstPoint.getX();
            this.y = secondPoint.getY() - this.firstPoint.getY();
            this.z = secondPoint.getZ() - this.firstPoint.getZ();
        }

        return this;
    }

    public Vector change(double deltaX, double deltaY, double deltaZ) {
        return this.changeX(deltaX).changeY(deltaY).changeZ(deltaZ);
    }

    public Vector change(Vector deltaVector) {
        return change(deltaVector.getX(), deltaVector.getY(), deltaVector.getZ());
    }

    public Vector changeX(double deltaX) {
        this.x += deltaX;

        if (Objects.nonNull(this.firstPoint) && Objects.nonNull(this.secondPoint)) {
            this.secondPoint.setX(this.firstPoint.getX() + this.x);
        }

        return this;
    }

    public Vector changeY(double deltaY) {
        this.y += deltaY;

        if (Objects.nonNull(this.firstPoint) && Objects.nonNull(this.secondPoint)) {
            this.secondPoint.setY(this.firstPoint.getY() + this.y);
        }

        return this;
    }

    public Vector changeZ(double deltaZ) {
        this.z += deltaZ;

        if (Objects.nonNull(this.firstPoint) && Objects.nonNull(this.secondPoint)) {
            this.secondPoint.setZ(this.firstPoint.getZ() + this.z);
        }

        return this;
    }

    public Vector add(Vector vector) {
        return VectorOperation.internalAdd(this, vector);
    }

    public Vector sub(Vector vector) {
        return VectorOperation.internalSub(this, vector);
    }

    public Vector mult(double ratio) {
        return VectorOperation.internalMult(this, ratio);
    }

    public Vector div(double ratio) {
        return VectorOperation.internalDiv(this, ratio);
    }

    public Vector toUnit() {
        return VectorOperation.toUnit(this);
    }

    public Vector getUnit() {
        return VectorOperation.getUnit(this);
    }

    public double getScalar() {
        return VectorOperation.getScalar(this);
    }

    public Vector rotateByZ(double angleInRad) {
        return VectorOperation.internalRotateByZ(this, angleInRad);
    }
}
