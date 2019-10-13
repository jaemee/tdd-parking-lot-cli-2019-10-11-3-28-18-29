package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParkingBoy {

    private String lastErrorMessage;
    private List<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLots = Collections.singletonList(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        ParkingLot parkingLot = parkingLots.stream().filter(ParkingLot::hasSpace).findFirst().orElse(null);
        if(parkingLot == null){
            lastErrorMessage = "Not enough position.";
            return null;
        }
        return parkingLot.park(car, parkingTicket);
    }

    public Car fetch(ParkingTicket ticket) {
        Car car = getCarFromParkingCarList(ticket);
        if(ticket == null){
            lastErrorMessage = "Please provide your parking ticket.";
        }else if(car == null){
            lastErrorMessage = "Unrecognized parking ticket.";
        }

        return car;
    }

    private Car getCarFromParkingCarList(ParkingTicket ticket) {
        for(ParkingLot parkingLot : parkingLots){
            return parkingLot.fetch(ticket);
        }
        return null;
    }


    public String getLastErrorMessage() {
        return lastErrorMessage;
    }


}
