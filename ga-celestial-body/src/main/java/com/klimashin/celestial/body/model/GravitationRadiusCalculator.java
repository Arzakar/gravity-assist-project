package com.klimashin.celestial.body.model;

import static com.klimashin.celestial.body.model.Constant.GRAVITATIONAL_CONSTANT;
import static com.klimashin.celestial.body.model.Constant.MAX_INTENSITY_GRAVITATION_FIELD;
import static java.util.Locale.ENGLISH;

public class GravitationRadiusCalculator {

    public long calculateGravitationRadius(double mass) {
        double rawMinRadius = Math.sqrt((GRAVITATIONAL_CONSTANT * mass) / MAX_INTENSITY_GRAVITATION_FIELD);
        return simplifyMinRadius(mass, rawMinRadius);
    }

    private long simplifyMinRadius(double mass, double rawMinRadius) {
        long simplifiedMinRadius = (long) rawMinRadius;
        StringBuilder simplifiedMinRadiusAsString = new StringBuilder(Long.toString(simplifiedMinRadius));

        int offsetIndex = simplifiedMinRadiusAsString.length() - 1;

        double intensity = GRAVITATIONAL_CONSTANT * (mass / Math.pow(simplifiedMinRadius, 2));
        double delta = calculateDelta(intensity);

        while (delta == 0.00) {
            simplifiedMinRadiusAsString.setCharAt(offsetIndex, '0');
            simplifiedMinRadius = Long.parseLong(simplifiedMinRadiusAsString.toString());

            intensity = GRAVITATIONAL_CONSTANT * (mass / Math.pow(simplifiedMinRadius, 2));
            delta = calculateDelta(intensity);

            offsetIndex--;
        }

        return simplifiedMinRadius;
    }

    private double calculateDelta(double intensity) {
        double rawDelta = Math.abs((1 - intensity / MAX_INTENSITY_GRAVITATION_FIELD) * 100);
        return Double.parseDouble(String.format(ENGLISH, "%.2f", rawDelta));
    }
}
