package com.klimashin.modeler.model.control;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ControlUnitContainer {

    final ControlUnit controlUnit;
    final Long startTimestamp;
    Long finishTimestamp;

}
