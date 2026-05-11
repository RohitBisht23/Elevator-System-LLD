package org.example;

import org.example.Enums.ElevatorDirection;

public class Floor {
    private int floorNumber;
    private ExternalButton upButton;
    private ExternalButton downButton;

    public Floor(int floorNumber,ExternalDispatcher dispatcher) {
        this.downButton = new ExternalButton(dispatcher);
        this.floorNumber = floorNumber;
        this.upButton = new ExternalButton(dispatcher);
    }

    public void pressUpButton() {
        upButton.pressButton(floorNumber, ElevatorDirection.UP);
    }

    public void pressDownButton() {
        downButton.pressButton(floorNumber, ElevatorDirection.DOWN);
    }
}
