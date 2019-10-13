package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    private Map<ParkingTicket, Car> cars = new HashMap<>();

    public ParkingLot() {
        this(10);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailableParkingSpace() {
        return capacity - cars.size();
    }

    public ParkingTicket park(Car car, ParkingTicket parkingTicket) {
        cars.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket ticket) {
        Car car = new Car();
        car = cars.get(ticket);
        cars.remove(ticket);
        return car;
    }

    public boolean hasSpace() {
        return cars.size() <= capacity;
    }

    public int getCapacity() {
        return this.capacity;
    }
}
