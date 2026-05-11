package org.example;

import org.example.Enums.DoorState;

public class Door {
    private DoorState doorState;

    public Door() {
        doorState = DoorState.CLOSED_DOOR;
    }

    public void openDoor(int id) {
        doorState = DoorState.OPENED_DOOR;
        System.out.println("Opening the Elevator door of elevator:" + id);
    }

    public void closeDoor(int id) {
        doorState = DoorState.CLOSED_DOOR;
        System.out.println("closing the Elevator door of elevator:" + id);
    }
}
