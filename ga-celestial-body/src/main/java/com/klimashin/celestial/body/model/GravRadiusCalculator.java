package com.klimashin.celestial.body.model;

import static com.klimashin.celestial.body.model.Constant.GRAVITATIONAL_CONSTANT;
import static com.klimashin.celestial.body.model.Constant.MAX_INTENSITY_GRAVITATION_FIELD;
import static java.util.Locale.ENGLISH;

public class GravRadiusCalculator {

    public static double calculateGravRadius(double mass) {
        double rawMinRadius = Math.sqrt((GRAVITATIONAL_CONSTANT * mass) / MAX_INTENSITY_GRAVITATION_FIELD);
        return simplifyMinRadius(mass, rawMinRadius);
    }

    private static double simplifyMinRadius(double mass, double rawMinRadius) {
        long simplifiedMinRadius = (long) rawMinRadius;

        double intensity = GRAVITATIONAL_CONSTANT * (mass / Math.pow(simplifiedMinRadius, 2));
        double delta = calculateDelta(intensity);
        long roundPeriod = 10;

        while (delta == 0.00) {
            simplifiedMinRadius = Math.round((double) simplifiedMinRadius / roundPeriod) * roundPeriod;

            intensity = GRAVITATIONAL_CONSTANT * (mass / Math.pow(simplifiedMinRadius, 2));
            delta = calculateDelta(intensity);

            roundPeriod *= 10;
        }

        return simplifiedMinRadius;
    }

    private static double calculateDelta(double intensity) {
        double rawDelta = Math.abs((1 - intensity / MAX_INTENSITY_GRAVITATION_FIELD) * 100);
        return Double.parseDouble(String.format(ENGLISH, "%.3f", rawDelta));
    }
}
