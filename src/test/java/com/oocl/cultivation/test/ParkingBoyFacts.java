package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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

        assertEquals(car, fetchedCar);
    }

    @Test
    void should_park_multiple_cars() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        Car car2 = new Car();

        ParkingTicket parkingTicket1 = parkingBoy.park(car);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);

        assertEquals(parkingBoy.fetch(parkingTicket1), car);
        assertEquals(parkingBoy.fetch(parkingTicket2), car2);
    }

    @Test
    void should_fetch_correct_car_by_parkingTicket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        ParkingTicket parkingTicket1 = parkingBoy.park(car);

        assertEquals(parkingBoy.fetch(parkingTicket1), car);
    }

    @Test
    void should_not_fetch_car_if_no_ticket_provided() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = new ParkingTicket();
        Car fetchedCar = parkingBoy.fetch(parkingTicket);

        assertNull(fetchedCar);
    }

    @Test
    void should_not_fetch_car_if_ticket_already_used() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);

        car = parkingBoy.fetch(parkingTicket);
        assertNotNull(car);

        car = parkingBoy.fetch(parkingTicket);
        assertNull(car);
    }

    @Test
    void should_not_able_to_park_if_no_position() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        IntStream.range(0, 10).mapToObj(car -> new Car()).
                forEach(parkingBoy::park);

        Car car = new Car();

        ParkingTicket parkingTicket = parkingBoy.park(car);

        assertNull(parkingTicket);
    }

    @Test
    void should_return_message_when_wrong_or_no_ticket_provided() {
        String expectedMessage = "Unrecognized parking ticket.";
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket ticket = (parkingBoy.park(car));

        car = parkingBoy.fetch(ticket);
        String message = parkingBoy.getLastErrorMessage();
        assertEquals(null, message);

        car = parkingBoy.fetch(ticket);
        message = parkingBoy.getLastErrorMessage();
        assertEquals(expectedMessage, message);

        parkingBoy = new ParkingBoy(parkingLot);
        ticket = new ParkingTicket();
        car = parkingBoy.fetch(ticket);
        message = parkingBoy.getLastErrorMessage();
        assertEquals(expectedMessage, message);
    }

    @Test
    void should_return_message_when_customer_not_provide_ticket() {
        String expectedMessage = "Please provide your parking ticket.";
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket ticket = (parkingBoy.park(car));

        car = parkingBoy.fetch(null);
        String message = parkingBoy.getLastErrorMessage();
        assertEquals(expectedMessage, message);
    }

    @Test
    void should_return_message_not_enough_position_when_parking_capacity_full() {
        String expectedMessage = "Not enough position.";

        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        IntStream.range(0, 10).mapToObj(car -> new Car()).
                forEach(parkingBoy::park);

        Car car = new Car();

        parkingBoy.park(car);
        String message = parkingBoy.getLastErrorMessage();
        assertEquals(expectedMessage, message);
    }

    @Test
    void should_park_to_parking_2_when_parking_1_is_full() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        IntStream.rangeClosed(1, 10).mapToObj(car -> new Car()).
                forEach(parkingBoy::park);

        Car car = new Car();
        ParkingTicket ticket = parkingBoy.park(car);
        assertNotEquals(parkingLot1.fetch(ticket), car);
        assertEquals(parkingLot2.fetch(ticket), car);
    }
}
