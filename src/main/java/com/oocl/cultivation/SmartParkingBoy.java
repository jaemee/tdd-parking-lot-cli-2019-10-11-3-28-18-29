package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    private List<ParkingLot> parkingLots = new ArrayList<>();
    private String lastErrorMessage;

    public SmartParkingBoy(ParkingLot parkingLot) {
       super(parkingLot);
       this.parkingLots = Collections.singletonList(parkingLot);
    }

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.parkingLots = parkingLots;
    }

    @Override
    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        ParkingLot parkingLot = parkingLots.stream().filter(ParkingLot::hasSpace)
                .reduce((curr, saved) -> saved.getAvailableParkingSpace() < curr
                        .getAvailableParkingSpace() ? curr : saved)
                .orElse(null);
        if(parkingLot == null){
            lastErrorMessage = "Not enough position.";
            return null;
        }
        return parkingLot.park(car, parkingTicket);
    }

    @Override
    public String getLastErrorMessage() {
        return super.getLastErrorMessage();
    }
}
