package org.events;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Concert extends Event{

    // fields
    private LocalTime time;
    private BigDecimal price;


    // constructors
    public Concert(String title, LocalDate date, int totalSeats, int bookedSeats, LocalTime time, BigDecimal price) {
        super(title, date, totalSeats);
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
    private void isValidTime(LocalTime time){
        if (time.isBefore(LocalTime.now())){
            throw new IllegalArgumentException("Time can't be in the past.");
        }
    }

    @Override
    public String toString() {
        return getTitle() + " - " +  time + " - "
                + "€ " + String.format("%.2f", getPrice());
    }
}
