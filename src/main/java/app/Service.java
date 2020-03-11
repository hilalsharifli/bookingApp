package app;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class Service {
    Scanner scan = new Scanner(System.in);
    int input;
    int idInput;
    DAO<FlightSchedule> dao = new DAOFlightsFileText("flights.txt");

    void menu() {
        System.out.println("1. Online-Board");
        System.out.println("2. Show the Flight information");
        System.out.println("3. Search and book a flight");
        System.out.println("4. Cancel the booking");
        System.out.println("5. My Flights");
        System.out.println("6. Exit");
    }

    void printFlights() {
        System.out.println("----");
        dao.getAll().stream().forEach(System.out::println);
        System.out.println("----");
    }

    void printFlightByID() {
        System.out.println("Please enter Flight ID: ");
        idInput = scan.nextInt();
        System.out.println(dao.get(idInput).toString());
    }

    void flightBooking() {
        System.out.print("Please enter destination address: ");
        String destination = scan.next();
        System.out.print("Please enter flight date like this 'yyyy-mm-dd': ");
        String flightDate = scan.next();
        System.out.print("Please enter passenger count: ");
        int passengerCount = scan.nextInt();

        System.out.println(dao.getAllBy(p -> (p.Destination.equals(destination) && p.Seats >= passengerCount && p.flightTime.toLocalDate().toString().equals(flightDate))) + "\n");
    }

    void flightCancel() {
        System.out.println("Flight is cancelled");
    }

    void myFlightShow() {
        System.out.println("You have below flights");
    }

    void selectMenu() {
        do {
            input = scan.nextInt();
            switch (input) {
                case 1: printFlights();
                    break;
                case 2: printFlightByID();
                    break;
                case 3: flightBooking();
                    break;
                case 4: flightCancel();
                    break;
                case 5: myFlightShow();
                    break;
                case 6: System.out.println("Bye Bye");
                    break;
                case 0: menu();
                    break;
            }
        } while (input != 6);
    }

}
