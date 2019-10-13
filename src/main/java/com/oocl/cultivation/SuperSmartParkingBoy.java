package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SuperSmartParkingBoy extends SmartParkingBoy {
    private List<ParkingLot> parkingLots = new ArrayList<>();
    private String lastErrorMessage;

    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
        this.parkingLots = Collections.singletonList(parkingLot);
    }

    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.parkingLots = parkingLots;
    }



    @Override
    public String getLastErrorMessage() {
        return super.getLastErrorMessage();
    }


    @Override
    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        ParkingLot parkingLot = parkingLots.stream().filter(ParkingLot::hasSpace)
                .reduce((curr, saved) -> getAvailablePositionPercentage(saved) < getAvailablePositionPercentage(curr) ? curr : saved)
                .orElse(null);
        if(parkingLot == null){
            lastErrorMessage = "Not enough position.";
            return null;
        }
        return parkingLot.park(car, parkingTicket);
    }

    private double getAvailablePositionPercentage(ParkingLot parkingLot) {
        double availableSpace = parkingLot.getAvailableParkingSpace();
        double capacity = parkingLot.getCapacity();

        return availableSpace/capacity;
    }
}
