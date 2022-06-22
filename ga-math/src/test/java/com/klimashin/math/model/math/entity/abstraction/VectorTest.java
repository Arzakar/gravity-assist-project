package com.klimashin.math.model.math.entity.abstraction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.klimashin.math.model.math.entity.Position;

import lombok.NoArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class VectorTest {

    @NoArgsConstructor
    static class TestVector extends Vector<TestVector> {
        public TestVector(double x, double y, double z) {
            super(x, y, z);
        }

        public TestVector(Position firstPoint, Position secondPoint) {
            super(firstPoint, secondPoint);
        }

        public TestVector(double x, double y, double z, Position firstPoint, Position secondPoint) {
            super(x, y, z, firstPoint, secondPoint);
        }
    }

    @ParameterizedTest(name = "[{index}] Начальные значения XYZ = [{0}, {1}, {2}]")
    @MethodSource("valuesXYZ")
    @DisplayName("Должен создать вектор с пустыми начальной и конечной точками")
    void shouldCreateVectorByAbsoluteValues(double x, double y, double z) {
        TestVector actualResult = new TestVector(x, y, z);
        TestVector expectedResult = new TestVector().setX(x).setY(y).setZ(z);

        assertNull(actualResult.getFirstPoint());
        assertNull(actualResult.getSecondPoint());
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest(name = "[{index}] Начальная точка = {0}, Конечная точка = {1}")
    @MethodSource("firstAndSecondPoints")
    @DisplayName("Должен создать вектор и заполнить абсолютные значения XYZ")
    void shouldCreateVectorByPoints(Position firstPoint, Position secondPoint) {
        TestVector actualResult = new TestVector(firstPoint, secondPoint);
        TestVector expectedResult = new TestVector(secondPoint.getX() - firstPoint.getX(),
                secondPoint.getY() - firstPoint.getY(),
                secondPoint.getZ() - firstPoint.getZ(),
                firstPoint,
                secondPoint);

        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest(name = "[{index}] Начальная точка = [{0}, {1}, {2}]")
    @MethodSource("valuesXYZ")
    @DisplayName("Должен задать значение начальной точки, и если есть конечная точка, то рассчитать координаты вектора")
    void shouldSetFirstPoint(double x, double y, double z) {
        Position firstPoint = new Position(x, y, z);
        Position secondPoint = new Position(x + 10, y + 10, z + 10);

        TestVector originSpeedWithoutSecondPoint = new TestVector();
        TestVector expectedSpeedWithoutSecondPoint = new TestVector(0, 0, 0, firstPoint, null);

        assertEquals(expectedSpeedWithoutSecondPoint, originSpeedWithoutSecondPoint.setFirstPoint(firstPoint));

        TestVector originSpeedWithSecondPoint = new TestVector().setSecondPoint(secondPoint);
        TestVector expectedSpeedWithSecondPoint = new TestVector(10, 10, 10, firstPoint, secondPoint);

        assertEquals(expectedSpeedWithSecondPoint, originSpeedWithSecondPoint.setFirstPoint(firstPoint));
    }

    @ParameterizedTest(name = "[{index}] Конечная точка = [{0}, {1}, {2}]")
    @MethodSource("valuesXYZ")
    @DisplayName("Должен задать значение конечной точки, и если есть начальная точка, то рассчитать координаты вектора")
    void shouldSetSecondPoint(double x, double y, double z) {
        Position secondPoint = new Position(x, y, z);
        Position firstPoint = new Position(x - 10, y - 10, z - 10);

        TestVector originSpeedWithoutFirstPoint = new TestVector();
        TestVector expectedSpeedWithoutFirstPoint = new TestVector(0, 0, 0, null, secondPoint);

        assertEquals(expectedSpeedWithoutFirstPoint, originSpeedWithoutFirstPoint.setSecondPoint(secondPoint));

        TestVector originSpeedWithFirstPoint = new TestVector().setFirstPoint(firstPoint);
        TestVector expectedSpeedWithFirstPoint = new TestVector(10, 10, 10, firstPoint, secondPoint);

        assertEquals(expectedSpeedWithFirstPoint, originSpeedWithFirstPoint.setSecondPoint(secondPoint));
    }

    @ParameterizedTest(name = "[{index}] Дельты XYZ = [{0}, {1}, {2}]")
    @MethodSource("valuesXYZ")
    @DisplayName("Должен изменить все координаты скорости, без изменения начальной и конечной точек")
    void shouldChangeOnlyAbsoluteValues(double deltaX, double deltaY, double deltaZ) {
        TestVector originSpeedWithoutPoints = new TestVector(10, 5, 25);
        TestVector expectedResultWithoutPoints = new TestVector(10 + deltaX, 5 + deltaY, 25 + deltaZ);
        assertEquals(expectedResultWithoutPoints, originSpeedWithoutPoints.change(deltaX, deltaY, deltaZ));

        TestVector originSpeedWithFirstPoint = new TestVector(10, 5, 25, new Position(0, 0, 0), null);
        TestVector expectedResultWithFirstPoint = new TestVector(10 + deltaX, 5 + deltaY, 25 + deltaZ, new Position(0, 0, 0), null);
        assertEquals(expectedResultWithFirstPoint, originSpeedWithFirstPoint.change(deltaX, deltaY, deltaZ));

        TestVector originSpeedWithSecondPoint = new TestVector(10, 5, 25, null, new Position(0, 0, 0));
        TestVector expectedResultWithSecondPoint = new TestVector(10 + deltaX, 5 + deltaY, 25 + deltaZ, null, new Position(0, 0, 0));
        assertEquals(expectedResultWithSecondPoint, originSpeedWithSecondPoint.change(deltaX, deltaY, deltaZ));
    }

    @ParameterizedTest(name = "[{index}] Дельты XYZ = [{0}, {1}, {2}]")
    @MethodSource("valuesXYZ")
    @DisplayName("Должен изменить все координаты скорости и скорректировать вторую точку")
    void shouldChangeWithSecondPoint(double deltaX, double deltaY, double deltaZ) {
        TestVector originSpeed = new TestVector(10, 5, 25,
                new Position(0, 0, 0),
                new Position(10, 5, 25));

        TestVector expectedResult = new TestVector(10 + deltaX, 5 + deltaY, 25 + deltaZ,
                new Position(0, 0, 0),
                new Position(10 + deltaX, 5 + deltaY, 25 + deltaZ));

        assertEquals(expectedResult, originSpeed.change(deltaX, deltaY, deltaZ));
    }

    private static Stream<Arguments> valuesXYZ() {
        return Stream.of(
                Arguments.of(5, 10, 15),
                Arguments.of(-5, 10, 0),
                Arguments.of(0, -7, 29)
        );
    }

    private static Stream<Arguments> firstAndSecondPoints() {
        return Stream.of(
                Arguments.of(new Position(0, 0, 0), new Position(5, 10, 15)),
                Arguments.of(new Position(2, 5, 3), new Position(-3, 15, 3)),
                Arguments.of(new Position(0, 1, -10), new Position(0, -6, 19))
        );
    }

}