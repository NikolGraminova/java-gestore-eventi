package org.events;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Event event = null;

        boolean run = true;
        while (run) {
            System.out.println("Enter new event.");
            System.out.print("Event name: ");
            String name = scan.nextLine();
            System.out.print("Event date (yyyy-MM-dd): ");
            LocalDate date = LocalDate.parse(scan.nextLine());
            System.out.print("Total seats: ");
            int totalSeats = Integer.parseInt(scan.nextLine());
            event = new Event(name, date, totalSeats, 0);
            System.out.println("Event added: " + event);

            System.out.print("Do you want to book seats for this event? Y/N ");
            String choiceBook = scan.nextLine();
            if (choiceBook.equalsIgnoreCase("y")) {
                System.out.println("Enter the amount of seats you want to book.");
                int seats = Integer.parseInt(scan.nextLine());
                event.book(seats);
                System.out.println(event);
            } else if (choiceBook.equalsIgnoreCase("n")) {
                System.out.println("Closing program.");
                run = false;
            } else {
                System.out.println("Invalid choice.");
            }

            System.out.print("Do you want to cancel seats for this event? (Y/N) ");
            String choiceCancel = scan.nextLine();
            if (choiceCancel.equalsIgnoreCase("y")) {
                System.out.println("Enter the amount of seats you want to cancel.");
                int seats = Integer.parseInt(scan.nextLine());
                event.cancel(seats);
                System.out.println(event);
            } else if (choiceCancel.equalsIgnoreCase("n")) {
                System.out.println("Closing program.");
                run = false;
            } else {
                System.out.println("Invalid choice.");
            }
        }
        scan.close();
    }
}