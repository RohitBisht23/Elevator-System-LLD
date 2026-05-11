package org.example;

import org.example.Enums.ElevatorDirection;

import java.util.Collection;
import java.util.concurrent.PriorityBlockingQueue;

public class ElevatorController implements Runnable{
    private ElevatorCar elevatorCar;
    private PriorityBlockingQueue<Integer> upMinPQ;
    private PriorityBlockingQueue<Integer> downMaxPQ;

    private final Object monitor = new Object();

    public ElevatorController(ElevatorCar elevatorCar) {
        this.elevatorCar = elevatorCar;
        upMinPQ = new PriorityBlockingQueue<>();
        downMaxPQ = new PriorityBlockingQueue<>(10, (a,b) -> b-a);
    }

    public void submitRequest(int destinationFloor) {
        enqueueRequest(destinationFloor);
    }

    private void enqueueRequest(int destinationFloor) {
        System.out.println("Request details -> destination floor :"+destinationFloor+" accepted by elevator :"+elevatorCar.getId());

        if(destinationFloor == elevatorCar.getNextStoppage()) {
            return;
        }

        if(destinationFloor >= elevatorCar.getNextStoppage()) {
            if(!upMinPQ.contains(destinationFloor)) {
                upMinPQ.add(destinationFloor);
            }
        } else {
            if(!downMaxPQ.contains(destinationFloor)) {
                downMaxPQ.add(destinationFloor);
            }
        }

        synchronized (monitor) {
            monitor.notify(); //Wake elevator thread
        }
    }

    @Override
    public void run() {
        controlElevator();
    }

    public void controlElevator() {
        while (true) {

            //no request, go to sleep
            synchronized (monitor) {
                while (upMinPQ.isEmpty() && downMaxPQ.isEmpty()) {
                    try {
                        System.out.println("elevator :"+elevatorCar.getId()+" is IDLE");
                        elevatorCar.setMovingDirection(ElevatorDirection.IDLE);
                        monitor.wait(); //Sleep until request is arrives
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }

            while(!upMinPQ.isEmpty()) {
                int floor = upMinPQ.poll();
                System.out.println("Serving floor :"+floor+" by elevator :"+elevatorCar.getId()+" current floor :"+elevatorCar.getCurrentFloor());
                elevatorCar.moveElevator(floor);
            }


            while(!downMaxPQ.isEmpty()) {
                int floor = downMaxPQ.poll();
                System.out.println("Serving floor :"+floor+" by elevator :"+elevatorCar.getId()+" current floor :"+elevatorCar.getCurrentFloor());
                elevatorCar.moveElevator(floor);
            }
        }
    }

    public ElevatorCar getElevatorCar() {
        return this.elevatorCar;
    }

    public PriorityBlockingQueue getUpMinPQ() {
        return this.upMinPQ;
    }


    public PriorityBlockingQueue getDownMaxPQ() {
        return this.downMaxPQ;
    }
}
