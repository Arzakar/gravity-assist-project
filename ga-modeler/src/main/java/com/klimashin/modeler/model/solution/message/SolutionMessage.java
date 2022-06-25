package com.klimashin.modeler.model.solution.message;

import com.klimashin.modeler.model.environment.ModelType;
import com.klimashin.modeler.model.resolver.TerminationReason;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SolutionMessage {

    TerminationReason terminationReason;
    ModelType modelType;
    Map<String, Object> modelEnvironmentParams;
    Map<String, Object> modelResolverParams;
    List<Map<String, Object>> spacecraftControlLawParams;
    Map<String, Object> solutionPurposeParams;

}
