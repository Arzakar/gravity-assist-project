package com.klimashin.celestial.body.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GravitationRadiusCalculatorTest {

    private final GravitationRadiusCalculator calculator = new GravitationRadiusCalculator();

    @ParameterizedTest
    @MethodSource("massAndGravitationRadiusMap")
    @DisplayName("Метод должен вернуть упрощённое значение гравитационного радиуса с погрешностью в 2 знака после запятой")
    void calculateGravitationRadiusTest(double mass, long expectedRadius) {
        assertEquals(expectedRadius, calculator.calculateGravitationRadius(mass));
    }

    private static Stream<Arguments> massAndGravitationRadiusMap() {
        return Stream.of(
                Arguments.of(333_022 * Math.pow(10, 18), 354_200_000L),
                Arguments.of(4_867_500 * Math.pow(10, 18), 1_354_100_000L),
                Arguments.of(641_710 * Math.pow(10, 18), 491_600_000L),
                Arguments.of(1_898_600 * Math.pow(10, 21), 26_744_000_000L)
        );
    }
}