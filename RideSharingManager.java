import java.util.Scanner;


public class RideSharingManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SinglyLinkedList rideList = new SinglyLinkedList();
        int choice = 0;
        
        rideList.insertAtEnd(new RideRequest(101, "John Doe", "123 Maple St", "456 Oak Ave", 15.50f));
        rideList.insertAtEnd(new RideRequest(102, "Jane Smith", "789 Pine Ln", "101 Birch Rd", 8.75f));
        rideList.insertAtEnd(new RideRequest(103, "Peter Jones", "212 Elm Ct", "313 Cedar Dr", 22.00f));


        while (choice != 8) {
            printMenu();
            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addRide(scanner, rideList);
                        break;
                    case 2:
                        deleteRide(scanner, rideList);
                        break;
                    case 3:
                        searchRide(scanner, rideList);
                        break;
                    case 4:
                        rideList.displayAll();
                        break;
                    case 5:
                        rideList.sortRidesByFare();
                        break;
                    case 6:
                        rideList.printInReverse();
                        break;
                    case 7:
                        float totalFare = rideList.calculateTotalFare();
                        System.out.printf("%n--- Total Fare of All Rides: $%.2f%n%n", totalFare);
                        break;
                    case 8:
                        System.out.println("Exiting the program. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- Ride Sharing Booking Manager ---");
        System.out.println("1. Add Ride Request (Insert at End)");
        System.out.println("2. Delete Ride by ID");
        System.out.println("3. Search Ride by ID");
        System.out.println("4. Display All Rides");
        System.out.println("5. Sort Rides by Fare (Insertion Sort)");
        System.out.println("6. Print Rides in Reverse (Recursion)");
        System.out.println("7. Calculate Total Fare (Recursion)");
        System.out.println("8. Exit");
        System.out.print("Enter choice: ");
    }


private static void addRide(Scanner scanner, SinglyLinkedList rideList) {
    try {
        System.out.print("Enter Ride ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        //ID uniqueness
        if (rideList.searchById(id) != null) {
            System.out.println("Error: A ride with ID " + id + " already exists. Please use a unique ID.");
            return; 
        }
        
        System.out.print("Enter Passenger Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Pickup Location: ");
        String pickup = scanner.nextLine();
        System.out.print("Enter Dropoff Location: ");
        String dropoff = scanner.nextLine();
        System.out.print("Enter Fare: ");
        float fare = Float.parseFloat(scanner.nextLine());

        RideRequest newRide = new RideRequest(id, name, pickup, dropoff, fare);
        rideList.insertAtEnd(newRide);
        System.out.println("Ride request added successfully!");
    } catch (NumberFormatException e) {
        System.out.println("Invalid input format. Please try again.");
    }
}

    private static void deleteRide(Scanner scanner, SinglyLinkedList rideList) {
        try {
            System.out.print("Enter Ride ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());
            if (rideList.deleteById(id)) {
                System.out.println("Ride with ID " + id + " deleted successfully.");
            } else {
                System.out.println("Ride with ID " + id + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a number.");
        }
    }

    private static void searchRide(Scanner scanner, SinglyLinkedList rideList) {
        try {
            System.out.print("Enter Ride ID to search for: ");
            int id = Integer.parseInt(scanner.nextLine());
            RideRequest foundRide = rideList.searchById(id);
            if (foundRide != null) {
                System.out.println("Ride found:");
                foundRide.display();
            } else {
                System.out.println("Ride with ID " + id + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a number.");
        }
    }
}