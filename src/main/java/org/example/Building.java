package org.example;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private List<Floor> floors = new ArrayList<>();

    public Building(int floor, ExternalDispatcher dispatcher) {
        for(int i=1; i<=floor; i++) {
            floors.add(new Floor(i, dispatcher));
        }
    }

    public Floor getFloor(int floor) {
        return floors.get(floor-1);
    }

}
