package org.events;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Concert extends Event{

    // fields
    LocalTime time;
    BigDecimal price;


    // constructors
    public Concert(String title, LocalDate date, int totalSeats, int bookedSeats, LocalTime time, BigDecimal price) {
        super(title, date, totalSeats, bookedSeats);
        isValidTime(time);
        this.time = time;
        this.price = price;
    }


    // getters and setters
    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {
        isValidTime(time);
        this.time = time;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    // methods
    public void isValidTime(LocalTime time){
        if (time.isBefore(LocalTime.now())){
            throw new RuntimeException("Time can't be in the past.");
        }
    }

    @Override
    public String toString() {
        return title + " - " +  time + " - "
                + "€ " + String.format("%.2f", getPrice());
    }
}
