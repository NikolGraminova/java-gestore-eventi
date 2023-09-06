package org.events;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {

        Event event = new Event("Party", LocalDate.of(2023, 10, 20), 50 );
        Event event1 = new Event("Spectacle", LocalDate.of(2023, 11, 5), 50);
        Event event2 = new Event("Wedding", LocalDate.of(2023, 10, 20), 100);
        Concert concert = new Concert("Concert",LocalDate.of(2023, 10, 20),
                100, 0, LocalTime.of(19, 30), new BigDecimal("50.00"));

        ArrayList<Event> events = new ArrayList<>();
        PlanEvents planner = new PlanEvents("Events", events);

        planner.addEvent(event);
        planner.addEvent(concert);
        planner.addEvent(event1);
        planner.addEvent(event2);

        System.out.println(planner.getEventsOnDate(LocalDate.of(2023, 10, 20)));
        System.out.println("Number of total events: " + planner.getNumberOfEvents());

        // planner.removeAllEvents();
        System.out.println(planner);

    }
}
