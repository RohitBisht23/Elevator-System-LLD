package org.example;

import org.example.Enums.ElevatorDirection;

public class ExternalButton {
    private final ExternalDispatcher dispatcher;

    public ExternalButton(ExternalDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void pressButton(int floor, ElevatorDirection direction ) {
        dispatcher.submitExternalRequest(floor, direction);
    }
}
