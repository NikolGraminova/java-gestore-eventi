package org.events;

import java.time.LocalDate;

public class Event {

    // fields
    String title;
    LocalDate date;
    int totalSeats;
    int bookedSeats = 0;


    // constructors
    public Event(String title, LocalDate date, int totalSeats, int bookedSeats){
        this.title = title;
        isValidDate(date);
        this.date = date;
        isValidSeats(totalSeats);
        this.totalSeats = totalSeats;
        this.bookedSeats = bookedSeats;
    }


    // getters and setters
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        isValidDate(date);
        this.date = date;
    }
    public int getTotalSeats() {
        return totalSeats;
    }
    public int getBookedSeats() {
        return bookedSeats;
    }


    // methods
    public void book() throws RuntimeException{
        if (date.isBefore(LocalDate.now()) || (totalSeats-bookedSeats ==  0)){
            throw new RuntimeException("Error. Booking not completed.");
        } else bookedSeats += 1;
    }
    public void cancel() throws RuntimeException{
        if (date.isBefore(LocalDate.now()) || (bookedSeats ==  0)){
            throw new RuntimeException("Error. Booking not completed.");
        } else bookedSeats -= 1;
    }

    public void isValidDate(LocalDate date) throws RuntimeException{
        if (date.isBefore(LocalDate.now())){
            throw new RuntimeException("Date can't be in the past.");
        }
    }

    public void isValidSeats(int totalSeats) throws RuntimeException{
        if (totalSeats < 0){
            throw new RuntimeException("Total seats can't be negative.");
        }
    }

    @Override
    public String toString() {
        return date.getDayOfMonth() + "-" + date.getMonthValue() + "-" + date.getYear()
                + " " + " - " +title;
    }
}
