package org.example;

import org.example.Enums.ElevatorDirection;

public class ExternalDispatcher {
    private ElevatorScheduler scheduler;

    public ExternalDispatcher(ElevatorScheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void submitExternalRequest(int floor, ElevatorDirection direction) {
        ElevatorController controller = scheduler.assignElevator(floor, direction);

        controller.submitRequest(floor);
    }

}
