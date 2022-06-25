package com.klimashin.modeler.model.resolver;

import com.klimashin.modeler.model.environment.ModelEnvironment;

public interface ModelResolver {

    ModelEnvironment getModelEnvironment();

    TerminationReason getTerminationReason();

}
