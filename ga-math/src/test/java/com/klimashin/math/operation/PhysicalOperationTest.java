package com.klimashin.math.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class PhysicalOperationTest {

    @ParameterizedTest(name = "[{index}] Масса 1-го объекта = {0} кг, масса второго объекта = {1} кг, " +
            "расстояние между объектам = {2} м, ожидаемая гравитационная сила = {3} Н")
    @MethodSource("parametersForGravitationForce")
    @DisplayName("Должен вернуть силу притяжения в зависимости от массы объектов и расстояния между ними")
    void shouldReturnGravitationForce(double massFirstObject, double massSecondObject, double range, double expectedResult) {
        assertEquals(expectedResult, PhysicalOperation.gravitationForce(massFirstObject, massSecondObject, range), 0.000001);
    }

    private static Stream<Arguments> parametersForGravitationForce() {
        return Stream.of(
                Arguments.of(4E10, 4E12, 1.02E5, 1026.420607),
                Arguments.of(1.02E9, 5.675E7, 1.25E3, 2.472588),
                Arguments.of(6.724, 1.46275E14, 2E3, 0.016411)
        );
    }
}