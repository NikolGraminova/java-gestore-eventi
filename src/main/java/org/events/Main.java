package org.events;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/events";
        String user = "root";
        String password = "Myroot23?";

        String sqlDisplayEvents = "select * from events";
        String sqlAddEvent = "insert into events (title, date, total_seats) values (?, ?, ?)";
        String sqlBook = "update events set booked_seats = booked_seats + ? where id = ?";
        String sqlCancel = "update events set booked_seats = booked_seats - ? where id = ?";
        String sqlFindEvent = "select * from events where date = ?";

        try(Connection conn = DriverManager.getConnection(url, user,password)){
            Scanner scan = new Scanner(System.in);
            boolean run = true;

            while (run){
                System.out.println("1.Display events - 2.Add a new event - 3.Book seats - 4.Cancel bookings - 5.Find events by date - 6.Exit");
                System.out.print("Choose an option: ");

                int choice = Integer.parseInt(scan.nextLine());
                try{
                    switch (choice) {
                        case 1:
                            System.out.println("--- Displaying events ---");
                            try (PreparedStatement ps = conn.prepareStatement(sqlDisplayEvents)) {
                                try (ResultSet rs = ps.executeQuery()) {
                                    while (rs.next()) {
                                        String id = rs.getString("id");
                                        String title = rs.getString("title");
                                        LocalDate date = rs.getDate("date").toLocalDate();
                                        int total_seats = rs.getInt("total_seats");
                                        int booked_seats = rs.getInt("booked_seats");
                                        System.out.println(id + ". " + title + " - " + date + " - total seats: " + total_seats + " - booked seats: " + booked_seats);
                                    }
                                }
                            }
                            break;
                        case 2:
                            System.out.println("Enter new event.");
                            System.out.print("Event name: ");
                            String title = scan.nextLine();
                            System.out.print("Event date (yyyy-MM-dd): ");
                            LocalDate date = LocalDate.parse(scan.nextLine());
                            System.out.print("Total seats: ");
                            int totalSeats = Integer.parseInt(scan.nextLine());
                            Event event = new Event(title, date, totalSeats);
                            System.out.println("Event added: " + event);
                            try (PreparedStatement ps = conn.prepareStatement(sqlAddEvent)){
                                ps.setString(1,title);
                                ps.setDate(2, Date.valueOf(date));
                                ps.setInt(3, totalSeats);
                                int rowsUpdated = ps.executeUpdate();
                                if (rowsUpdated > 0){
                                    System.out.println("Event successfully added to database.");
                                } else {
                                    System.out.println("There was an error. Event not added.");
                                }
                            }
                            break;
                        case 3:
                            System.out.print("Select event to book seats: ");
                            int bookEventId = Integer.parseInt(scan.nextLine());
                            System.out.print("How many seats do you want to book?: ");
                            int bookSeatsNum = Integer.parseInt(scan.nextLine());
                            try (PreparedStatement ps = conn.prepareStatement(sqlBook)){
                                ps.setInt(2,bookEventId);
                                ps.setInt(1,bookSeatsNum);
                                int rowsUpdated = ps.executeUpdate();
                                if (rowsUpdated > 0){
                                    System.out.println("Seats successfully booked.");
                                } else {
                                    System.out.println("There was an error. Booking interrupted.");
                                }
                            }
                            break;
                        case 4:
                            System.out.print("Select event to cancel seats: ");
                            int cancelEventId = Integer.parseInt(scan.nextLine());
                            System.out.print("How many bookings do you want to cancel?: ");
                            int cancelSeatsNum = Integer.parseInt(scan.nextLine());
                            try (PreparedStatement ps = conn.prepareStatement(sqlCancel)){
                                ps.setInt(2,cancelEventId);
                                ps.setInt(1,cancelSeatsNum);
                                int rowsUpdated = ps.executeUpdate();
                                if (rowsUpdated > 0){
                                    System.out.println("Bookings successfully cancelled.");
                                } else {
                                    System.out.println("There was an error. Cancelling interrupted.");
                                }
                            }
                            break;
                        case 5:
                            System.out.print("Enter date: ");
                            LocalDate eventDate = LocalDate.parse(scan.nextLine());
                            try (PreparedStatement ps = conn.prepareStatement(sqlFindEvent)) {
                                ps.setDate(1,Date.valueOf(eventDate));
                                try (ResultSet rs = ps.executeQuery()) {
                                    while (rs.next()) {
                                        String id = rs.getString("id");
                                        String f_title = rs.getString("title");
                                        LocalDate f_date = rs.getDate("date").toLocalDate();
                                        int total_seats = rs.getInt("total_seats");
                                        int booked_seats = rs.getInt("booked_seats");
                                        System.out.println(id + ". " + f_title + " - " + f_date + " - total seats: " + total_seats + " - booked seats: " + booked_seats);
                                    }
                                }
                            }

                            break;
                        case 6:
                            System.out.println("Closing program.");
                            run = false;
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }

                } catch (InvalidEventParametersException ex){
                    System.out.println("Error while creating event: " + ex.getMessage());
                } catch (DateTimeParseException de){
                    System.out.println("Invalid date format.");
                } catch (NumberFormatException ne){
                    System.out.println("Invalid number format.");
                } catch (Exception e){
                    System.out.println(e.getMessage());
                    System.out.println("Generic error.");
                }
            }
            scan.close();
        } catch (SQLException e){
            System.out.println("SQL connection error.");
            e.printStackTrace();
        }
    }
}
