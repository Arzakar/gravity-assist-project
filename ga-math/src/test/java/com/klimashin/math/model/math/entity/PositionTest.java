package com.klimashin.math.model.math.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class PositionTest {

    @ParameterizedTest(name = "[{index}] Начальные значения XYZ = [{0}, {1}, {2}]")
    @MethodSource("valuesXYZ")
    @DisplayName("Должен создать позицию")
    void shouldCreateSpeedByAbsoluteValues(double x, double y, double z) {
        Position actualResult = new Position(x, y, z);
        Position expectedResult = new Position().setX(x).setY(y).setZ(z);

        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> valuesXYZ() {
        return Stream.of(
                Arguments.of( 5,  2,  3),
                Arguments.of( 7, -1,  4),
                Arguments.of( 0,  0, 10)
        );
    }
}