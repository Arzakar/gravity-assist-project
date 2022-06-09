package com.klimashin.math.dto.abstraction;

import com.klimashin.math.function.ArithmeticApi;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Vector implements VectorOperation {

    protected float x;
    protected float y;
    protected float z;

    protected Vector(Vector vector) {
        this.x = vector.getX();
        this.y = vector.getY();
        this.z = vector.getZ();
    }

    public void add(Vector vector) {
        this.x += vector.getX();
        this.y += vector.getY();
        this.z += vector.getZ();
    }

    public void sub(Vector vector) {
        this.x -= vector.getX();
        this.y -= vector.getY();
        this.z -= vector.getZ();
    }

    public float getModule() {
        return ArithmeticApi.getModule(this);
    }
}
