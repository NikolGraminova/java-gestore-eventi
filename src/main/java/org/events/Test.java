package org.events;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Test {
    public static void main(String[] args) {
        Concert concert = new Concert("Title",LocalDate.of(2023, 9, 5),
                100, 0, LocalTime.of(19, 30), new BigDecimal("50.00"));

        System.out.println(concert);

    }
}
