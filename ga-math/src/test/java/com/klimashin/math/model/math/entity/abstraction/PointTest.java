package com.klimashin.math.model.math.entity.abstraction;

import lombok.NoArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointTest {

    @NoArgsConstructor
    static class TestPoint extends Point<TestPoint> {

        public TestPoint(double x, double y, double z) {
            super(x, y, z);
        }
    }

    @ParameterizedTest(name = "[{index}] Дельты XYZ = [{0}, {1}, {2}]")
    @MethodSource("valuesXYZ")
    @DisplayName("Должен изменить все координаты позиции")
    void shouldChange(double deltaX, double deltaY, double deltaZ) {
        TestPoint originPosition = new TestPoint(10, 5, 25);

        TestPoint expectedResult = new TestPoint(10 + deltaX, 5 + deltaY, 25 + deltaZ);

        assertEquals(expectedResult, originPosition.change(deltaX, deltaY, deltaZ));
    }

    private static Stream<Arguments> valuesXYZ() {
        return Stream.of(
                Arguments.of(5, 2, 3),
                Arguments.of(7, -1, 4),
                Arguments.of(0, 0, 10)
        );
    }
}