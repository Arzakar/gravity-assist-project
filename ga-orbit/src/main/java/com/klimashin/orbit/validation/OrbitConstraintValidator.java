package com.klimashin.orbit.validation;

import static com.klimashin.orbit.exception.message.OrbitExceptionMessage.CIRCULAR_ORBIT_EXCEPTION_MESSAGE;
import static com.klimashin.orbit.exception.message.OrbitExceptionMessage.ELLIPTIC_ORBIT_EXCEPTION_MESSAGE;
import static com.klimashin.orbit.exception.message.OrbitExceptionMessage.HYPERBOLIC_ORBIT_EXCEPTION_MESSAGE;
import static com.klimashin.orbit.exception.message.OrbitExceptionMessage.PARABOLIC_ORBIT_EXCEPTION_MESSAGE;

import com.klimashin.orbit.model.Orbit;

import java.util.ArrayList;
import java.util.List;

public class OrbitConstraintValidator implements Validator<Orbit> {

    private final List<String> errorMessages = new ArrayList<>();

    @Override
    public List<String> validate(Orbit orbit) {
        this.validateEccentricity(orbit);

        return errorMessages;
    }

    private void validateEccentricity(Orbit orbit) {
        float eccentricity = orbit.getEccentricity();

        switch (orbit.getOrbitType()) {
            case CIRCULAR -> {
                if (eccentricity != 0f) {
                    errorMessages.add(String.format(CIRCULAR_ORBIT_EXCEPTION_MESSAGE, eccentricity));
                }
            }
            case ELLIPTIC -> {
                if (eccentricity < 0 || eccentricity > 1) {
                    errorMessages.add(String.format(ELLIPTIC_ORBIT_EXCEPTION_MESSAGE, eccentricity));
                }
            }
            case PARABOLIC -> {
                if (eccentricity != 1f) {
                    errorMessages.add(String.format(PARABOLIC_ORBIT_EXCEPTION_MESSAGE, eccentricity));
                }
            }
            case HYPERBOLIC -> {
                if (eccentricity < 1) {
                    errorMessages.add(String.format(HYPERBOLIC_ORBIT_EXCEPTION_MESSAGE, eccentricity));
                }
            }
        }
    }
}
