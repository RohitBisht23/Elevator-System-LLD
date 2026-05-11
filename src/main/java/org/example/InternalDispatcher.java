package org.example;

public class InternalDispatcher {
    private static InternalDispatcher INSTANCE = new InternalDispatcher();

    private InternalDispatcher(){}


    public static InternalDispatcher getINSTANCE() {
        return INSTANCE;
    }


    // elevatorController is known based on button press origin
    public void submitInternalRequest(int destinationFloor, ElevatorController elevatorController) {
        elevatorController.submitRequest(destinationFloor);
    }
}
