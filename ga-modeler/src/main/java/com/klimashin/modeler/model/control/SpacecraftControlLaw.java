package com.klimashin.modeler.model.control;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Data
public class SpacecraftControlLaw {

    private List<ControlUnitContainer> controlUnitContainers;
    private ControlUnitContainer currentControlUnitContainer;

    public SpacecraftControlLaw(List<ControlUnitContainer> controlUnitContainers) {
        this.controlUnitContainers = controlUnitContainers;
        this.currentControlUnitContainer = controlUnitContainers.stream()
                .min(Comparator.comparing(ControlUnitContainer::getStartTimestamp))
                .orElseThrow();
    }

    /**
     * Метод достаёт из списка участков тот, в границы которого попадается переданный параметр.
     * Алгоритм: список контейнеров фильтруется исходя из условия, что стартовое время меньше переданного,
     * затем список сортируется по убыванию и возвращается первый элемент.
     * Для ускорения работы метода, кешируется текущий контейнер и перед выбором проверяется, находится ли переданное время
     * во временных границах контейнера или нет. Если последний элемент не имеет времени окончания, то он остаётся до конца расчёта
     */
    public ControlUnit getCurrentControlUnit(long currentTimestamp) {
        if (Objects.isNull(currentControlUnitContainer.getFinishTimestamp())
                || currentControlUnitContainer.getFinishTimestamp() > currentTimestamp) {
            return currentControlUnitContainer.getControlUnit();
        }

        currentControlUnitContainer = controlUnitContainers.stream()
                .filter(container -> container.getStartTimestamp() <= currentTimestamp)
                .min((container1, container2) -> container2.getStartTimestamp().compareTo(container1.getStartTimestamp()))
                .orElseThrow(() -> new RuntimeException("Переданное время не попадает ни в один промежуток"));

        return currentControlUnitContainer.getControlUnit();
    }

}
