package org.events;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        LocalDate date = LocalDate.of(2023,9,6);

        Event event = new Event("eventonome", date, 3,0);
        System.out.println(event);
        System.out.println(event.getBookedSeats());
        event.book();
        System.out.println(event.getBookedSeats());
        event.cancel();
        System.out.println(event.getBookedSeats());
        System.out.println(event);

    }
}