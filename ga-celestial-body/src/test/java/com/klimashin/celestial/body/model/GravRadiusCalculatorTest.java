package com.klimashin.celestial.body.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_WITH_NAMES_PLACEHOLDER;

@ExtendWith(MockitoExtension.class)
class GravRadiusCalculatorTest {

    @ParameterizedTest(name = "[{index}] Масса = {0} кг, Ожидаемый радиус = {1} м")
    @MethodSource("massAndGravitationRadiusMap")
    @DisplayName("Должен вернуть упрощённое значение гравитационного радиуса с погрешностью в 3 знака после запятой")
    void calculateGravitationRadiusTest(double mass, long expectedRadius) {
        assertEquals(expectedRadius, GravRadiusCalculator.calculateGravRadius(mass));
    }

    private static Stream<Arguments> massAndGravitationRadiusMap() {
        return Stream.of(
                Arguments.of(333_022 * Math.pow(10, 18), 354_200_000L),
                Arguments.of(4_867_500 * Math.pow(10, 18), 1_354_190_000L),
                Arguments.of(641_710 * Math.pow(10, 18), 491_690_000L),
                Arguments.of(1_898_600 * Math.pow(10, 21), 26_750_000_000L)
        );
    }
}