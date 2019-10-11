package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ParkingBoyFacts {

    @Test
    void should_park_a_car_in_parking_lot() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        ParkingTicket parkingTicket = parkingBoy.park(car);

        assertNotNull(parkingTicket);
    }

    @Test
    void should_fetch_a_car_in_parking_lot_by_parking_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);

        Car fetchedCar = parkingBoy.fetch(parkingTicket);

        assertNotNull(fetchedCar);
    }

    @Test
    void should_park_multiple_cars() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        Car car2 = new Car();

        ParkingTicket parkingTicket1 = parkingBoy.park(car);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);

        assertEquals(parkingBoy.fetch(parkingTicket1),car);
        assertEquals(parkingBoy.fetch(parkingTicket2),car2);
    }

    @Test
    void should_fetch_correct_car_by_parkingTicket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        ParkingTicket parkingTicket1 = parkingBoy.park(car);

        assertEquals(parkingBoy.fetch(parkingTicket1),car);
    }

    @Test
    void should_not_fetch_car_if_no_ticket_provided() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Car fetchedCar = parkingBoy.fetch(null);

        assertNull(fetchedCar);
    }
}
