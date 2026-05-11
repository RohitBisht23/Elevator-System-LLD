package org.example;

import org.example.Enums.ElevatorDirection;

import java.util.List;

public class LeastBusyElevatorSelectionStrategy implements ElevatorSelectionStrategy{
    @Override
    public ElevatorController selectElevator(List<ElevatorController> controllers, int requestFloor, ElevatorDirection direction) {
        ElevatorController best = null;
        int minLoad = Integer.MAX_VALUE;

        for (ElevatorController controller : controllers) {
            int load = controller.getUpMinPQ().size() +
                    controller.getDownMaxPQ().size();

            if (load < minLoad) {
                minLoad = load;
                best = controller;
            }
        }
        return best;

    }
}
