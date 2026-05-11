package org.example;

import org.example.Enums.ElevatorDirection;

public class ElevatorCar {
    private int id;
    private ElevatorDirection movingDirection;
    private int currentFloor;
    private int nextStoppageFloor;
    private Door door;

    public ElevatorCar(int id) {
        this.id = id;
        currentFloor=0;
        movingDirection = ElevatorDirection.IDLE;
        door = new Door();
    }

    public void showDisplay() {
        System.out.println("elevator : "+id+" current floor :"+currentFloor+" moving :"+movingDirection);
    }

    public void moveElevator(int destinationFloor) {
        //This is demo object, if command has come it go in particular direction and particular floor, it just move
        //no matter what is current state and floor

        this.nextStoppageFloor = destinationFloor;
        if(this.currentFloor == destinationFloor) {
            door.openDoor(id);
            return;
        }

        int startFloor = this.currentFloor;
        door.closeDoor(id);

        if(nextStoppageFloor >= currentFloor) {
            movingDirection = ElevatorDirection.UP;
            showDisplay();

            //+1 i am doing because, floor start from 0,1,2... so if anyone goes from 1st floor to 2nd, so only 1 floor
            //lift has to move, not 2
            for(int i = startFloor+1;  i<= nextStoppageFloor; i++) {
                try {
                    Thread.sleep(5);
                } catch (Exception e) {

                }
                setCurrentFloor(i);
                showDisplay();
            }
        } else {
            movingDirection = ElevatorDirection.DOWN;
            showDisplay();

            for(int i = startFloor-1; i>= nextStoppageFloor; i--) {
                try {
                    Thread.sleep(5);
                } catch (Exception e) {

                }
                setCurrentFloor(i);
                showDisplay();
            }
        }
        door.openDoor(id);
    }


    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getId() {
        return this.id;
    }

    public int getNextStoppage() {
        return this.nextStoppageFloor;
    }

    public void setMovingDirection(ElevatorDirection elevatorDirection) {
        this.movingDirection = movingDirection;
    }

    public int getCurrentFloor() {
        return this.currentFloor;
    }

    public ElevatorDirection getMovingDirection() {
        return this.movingDirection;
    }
}
