package org.events;

import java.time.LocalDate;

public class Event {

    // fields
    private String title;
    private LocalDate date;
    private int totalSeats;
    private int bookedSeats;


    // constructors
    public Event(String title, LocalDate date, int totalSeats) throws InvalidEventParametersException{
        isEmptyString(title);
        this.title = title;
        isValidDate(date);
        this.date = date;
        isValidSeats(totalSeats);
        this.totalSeats = totalSeats;
        this.bookedSeats = 0;
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
    public void book(int seats){
        if (date.isBefore(LocalDate.now()) || (seats > totalSeats) || (seats <= 0)){
            throw new InvalidEventParametersException("Error. Booking not completed.");
        } else {
            bookedSeats += seats;
        }
    }
    public void cancel(int seats) {
        if (date.isBefore(LocalDate.now()) || (seats > bookedSeats) || (seats <= 0)){
            throw new InvalidEventParametersException("Error. Canceling not completed.");
        } else {
            bookedSeats -= seats;
        }
    }

    private void isEmptyString(String s){
        if (s.isEmpty()){
            throw new InvalidEventParametersException("Title can't be empty.");
        }
    }

    private void isValidDate(LocalDate date){
        if (date.isBefore(LocalDate.now())){
            throw new InvalidEventParametersException("Date can't be in the past.");
        }
    }

    private void isValidSeats(int totalSeats){
        if (totalSeats <= 0){
            throw new InvalidEventParametersException("Total seats can't be negative.");
        }
    }

    @Override
    public String toString() {
        return date.getDayOfMonth() + "-" + date.getMonthValue() + "-" + date.getYear()
                + " - " + title + " - booked seats: " +
                getBookedSeats() + " - available seats: " + getTotalSeats();
    }
}