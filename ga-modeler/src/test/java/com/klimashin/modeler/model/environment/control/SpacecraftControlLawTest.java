package com.klimashin.modeler.model.environment.control;

import com.klimashin.modeler.model.control.SpacecraftControlLaw;
import com.klimashin.modeler.model.control.implementation.BodyTargetControl;
import com.klimashin.modeler.model.control.implementation.ConstantDeviationControl;
import com.klimashin.modeler.model.control.ControlUnitContainer;
import com.klimashin.modeler.model.control.implementation.DynamicDeviationControl;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static com.klimashin.modeler.model.control.ControlType.BODY_TARGET;
import static com.klimashin.modeler.model.control.ControlType.CONSTANT_DEVIATION;
import static com.klimashin.modeler.model.control.ControlType.DYNAMIC_DEVIATION;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SpacecraftControlLawTest {

    @Test
    void shouldReturnCurrentUnit() {
        long startTimestamp = 0;

        List<ControlUnitContainer> testList = List.of(
                new ControlUnitContainer(new ConstantDeviationControl(), startTimestamp, startTimestamp + 999),
                new ControlUnitContainer(new DynamicDeviationControl(), startTimestamp + 1000, startTimestamp + 1999),
                new ControlUnitContainer(new BodyTargetControl(), startTimestamp + 2000, null)
        );

        SpacecraftControlLaw controlLaw = new SpacecraftControlLaw(testList);

        assertEquals(CONSTANT_DEVIATION, controlLaw.getCurrentControlUnit(startTimestamp).getControlType());
        assertEquals(CONSTANT_DEVIATION, controlLaw.getCurrentControlUnit(startTimestamp + 500).getControlType());

        assertEquals(DYNAMIC_DEVIATION, controlLaw.getCurrentControlUnit(startTimestamp + 1000).getControlType());
        assertEquals(DYNAMIC_DEVIATION, controlLaw.getCurrentControlUnit(startTimestamp + 1500).getControlType());

        assertEquals(BODY_TARGET, controlLaw.getCurrentControlUnit(startTimestamp + 2000).getControlType());
        assertEquals(BODY_TARGET, controlLaw.getCurrentControlUnit(startTimestamp + 2500).getControlType());
        assertEquals(BODY_TARGET, controlLaw.getCurrentControlUnit(startTimestamp + 10000).getControlType());
    }

}