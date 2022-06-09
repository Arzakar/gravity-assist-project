package com.klimashin.math.dto.abstraction;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Point implements PointOperation {

    protected float x;
    protected float y;
    protected float z;

    protected Point(Point point) {
        this.x = point.getX();
        this.y = point.getY();
        this.z = point.getZ();
    }

    public void add(Point point) {
        this.x += point.getX();
        this.y += point.getY();
        this.z += point.getZ();
    }

    public void sub(Point point) {
        this.x -= point.getX();
        this.y -= point.getY();
        this.z -= point.getZ();
    }
}
