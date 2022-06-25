package com.klimashin.modeler.model.solution.message;

import com.klimashin.modeler.model.environment.ModelEnvironment;
import com.klimashin.modeler.model.environment.ModelType;
import com.klimashin.modeler.model.resolver.CentralCelestialBodyResolver;
import com.klimashin.modeler.model.resolver.ModelResolver;

import com.klimashin.modeler.model.resolver.TerminationReason;
import lombok.experimental.UtilityClass;

import java.util.LinkedHashMap;
import java.util.Map;

@UtilityClass
public class SolutionMessageFactory {

    public SolutionMessage buildSolutionMessage(TerminationReason terminationReason, ModelResolver modelResolver) {
        ModelType modelType = modelResolver.getModelEnvironment().getModelType();

        SolutionMessage solutionMessage = SolutionMessage.builder()
                .terminationReason(terminationReason)
                .modelType(modelType)
                .build();

        return switch (modelType) {
            case CENTRAL_CELESTIAL_BODY -> fillMessageForCentralCelestialBody(modelResolver);
        };
    }

    public SolutionMessage fillMessageForCentralCelestialBody(ModelResolver modelResolver) {

        ModelEnvironment modelEnvironment = modelResolver.getModelEnvironment();
        Map<String, Object> modelEnvironmentParams = new LinkedHashMap<>();

        return SolutionMessage.builder().build();
    }

}
