import java.util.Scanner;

public class RideSharingManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SinglyLinkedList rideList = new SinglyLinkedList();
        
        //  demonstration
        rideList.insertAtEnd(new RideRequest(101, "Kgotso Dignity", "158 Rajeke Street", "Univen Road", 15.50f));
        rideList.insertAtEnd(new RideRequest(102, "Sedzani Wolf", "789 Louis Trichard ", "Thavhanai mall Rd", 8.75f));
        rideList.insertAtEnd(new RideRequest(103, "Peter Jones", "Hope Sithandiwe", "Elim mall", 22.00f));

         boolean continueRunning = true;
        while (continueRunning) {
            printMenu();

            int choice = getValidIntegerInput(scanner, "What do you want to do from the menu: ");

           switch (choice) {
                case 1:
                    addRide(scanner, rideList);
                    continueRunning = promptToContinue(scanner);
                    break;
                case 2:
                    deleteRide(scanner, rideList);
                    continueRunning = promptToContinue(scanner);
                    break;
                case 3:
                    searchRide(scanner, rideList);
                    continueRunning = promptToContinue(scanner);
                    break;
                case 4:
                    rideList.displayAll();
                    continueRunning = promptToContinue(scanner);
                    break;
                case 5:
                    rideList.sortRidesByFare();
                    continueRunning = promptToContinue(scanner);
                    break;
                case 6:
                    rideList.printInReverse();
                    continueRunning = promptToContinue(scanner);
                    break;
                case 7:
                    float totalFare = rideList.calculateTotalFare();
                    System.out.printf("%n--- Total Fare of All Rides: $%.2f%n", totalFare);
                    continueRunning = promptToContinue(scanner);
                    break;
                case 8:
                    continueRunning = false; 
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                    continueRunning = promptToContinue(scanner);
            }
        }
    }
// an option if user want to contienue or exit
private static boolean promptToContinue(Scanner scanner) {
        System.out.println("\nPress 1 to return to the main menu, or any other key to exit.");
        String input = scanner.nextLine();

 if (input.equals("1")) {
        return true; 
    } else {
        System.out.println("\nPlease wait...\nProcess terminated successfully.");
        return false; 
    }
}

   private static void printMenu() {
        System.out.println("\nWelcome, this is Ride Sharing Manager");

        System.out.println("1. Add Ride Request ");
        System.out.println("2. Delete Ride by ID");
        System.out.println("3. Search Ride by ID");
        System.out.println("4. Display All Rides");
        System.out.println("5. Sort Rides by Fare ");
        System.out.println("6. Print Rides in Reverse");
        System.out.println("7. Calculate Total Fare )");
        System.out.println("8. Exit");
    }
        
     //user should enter valid and a non string input.
     
    private static String getValidStringInput(Scanner scanner, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("we couldn't get from you. Please look at the menu and try again.");
            }
        }
    }

    //  //user should enter valid  and non integer input.
    private static int getValidIntegerInput(Scanner scanner, String prompt) {
        while (true) {
            try {

                String input = getValidStringInput(scanner, prompt);
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number(s).");
            }
        }
    }
        
      // non-negative fare price must be entered.
    private static float getValidFloatInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                String input = getValidStringInput(scanner, prompt);
                float value = Float.parseFloat(input);
                if (value >= 0) {
                    return value;
                } else {
                    System.out.println("You've entered a negative number. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number (e.g., 20.50).");
            }
        }
    }


    // LOGIC METHODS 
    private static void addRide(Scanner scanner, SinglyLinkedList rideList) {
        int id;

        while (true) {
            id = getValidIntegerInput(scanner, "Enter Ride ID: ");
            if (rideList.searchById(id) != null) {
                System.out.println("Error: A ride with ID " + id + " already exists. Please use a unique ID.");
            } else {
                break; 
            }
        }
        
        String name = getValidStringInput(scanner, "Enter Passenger Name: ");
        String pickup = getValidStringInput(scanner, "Enter Pickup Location: ");
        String dropoff = getValidStringInput(scanner, "Enter Dropoff Location: ");
        float fare = getValidFloatInput(scanner, "Enter Fare: ");

        RideRequest newRide = new RideRequest(id, name, pickup, dropoff, fare);
        rideList.insertAtEnd(newRide);
        System.out.println("Ride request added successfully!");
    }

    // deleteById method
    private static void deleteRide(Scanner scanner, SinglyLinkedList rideList) {
        int id = getValidIntegerInput(scanner, "Enter Ride ID to delete: ");

        if (rideList.deleteById(id)) {
            System.out.println("Ride with ID " + id + " deleted successfully.");
        } else {
            System.out.println("Ride with ID " + id + " not found.");
        }
    }

    private static void searchRide(Scanner scanner, SinglyLinkedList rideList) {
        int id = getValidIntegerInput(scanner, "Enter Ride ID to search for: ");
        RideRequest ride = rideList.searchById(id);
        if (ride != null) {
            System.out.println("\nRide found:");
            ride.display();
        } else {
            System.out.println("Ride with ID " + id + " not found.");
        }
    }
}
