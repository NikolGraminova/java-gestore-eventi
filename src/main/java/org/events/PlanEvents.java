package org.events;

import java.time.LocalDate;
import java.util.ArrayList;

public class PlanEvents {

    // fields
    private String title;
    private ArrayList<Event> events = new ArrayList<>();


    // constructors
    public PlanEvents(String title, ArrayList<Event> events) throws InvalidEventParametersException{
        isEmptyString(title);
        this.title = title;
        this.events = new ArrayList<>();
    }


    // getters and setters
    public String getTitle() {
        return title;
    }
    public ArrayList<Event> getEvents() {
        return events;
    }
    public void setTitle(String title) {
        isEmptyString(title);
        this.title = title;
    }


    // methods
    public void addEvent(Event event){
        events.add(event);
    }

    public ArrayList<Event> getEventsOnDate(LocalDate date) {
        ArrayList<Event> eventsOnDate = new ArrayList<>();
        for (Event event : events) {
            if (event.getDate().equals(date)) {
                eventsOnDate.add(event);
            }
        }
        return eventsOnDate;
    }

    public int getNumberOfEvents(){
        int num = 0;
        for (Event event : events){
            num += 1;
        } return num;
    }

    public void removeAllEvents(){
        events.clear();
    }

    private void isEmptyString(String s){
        if (s.isEmpty()){
            throw new InvalidEventParametersException("Title can't be empty.");
        }
    }

    @Override
    public String toString() {
        return  title + ": " + events;
    }
}
