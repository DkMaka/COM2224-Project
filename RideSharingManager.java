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

            int choice = getValidIntegerInput(scanner, "\nWhat do you want to do from the menu: ");

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
                    System.out.println("\nPlease wait...\nProcess terminated successfully."); // Added exit message for option 8
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                    continueRunning = promptToContinue(scanner);
            }
        }
        scanner.close(); // Close the scanner 
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
        System.out.println("\nWelcome, this is Ride Sharing Manager\n");

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


// ride request details 

class RideRequest {

    // Attributes
    private int rideId;
    private String passengerName;
    private String pickupLocation;
    private String dropoffLocation;
    private float fare;


    public RideRequest(int rideId, String passengerName, String pickupLocation, String dropoffLocation, float fare) {
        this.rideId = rideId; 
        this.passengerName = passengerName;
        this.pickupLocation = pickupLocation;   
        this.dropoffLocation = dropoffLocation; 
        this.fare = fare;
    }

    // information hiding by Encapsulation

    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) { 
        this.rideId = rideId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public float getFare() {
        return fare;
    }

    public void setFare(float fare) {
        this.fare = fare;
    }

    //  Display Method
    public void display() {
        System.out.println("------------------------------------");
        System.out.println("Ride ID: " + rideId); 
        System.out.println("Passenger Name: " + passengerName);
        System.out.println("Pickup Location: " + pickupLocation);
        System.out.println("Dropoff Location: " + dropoffLocation);
        System.out.printf("Fare: $%.2f%n", fare); 
        System.out.println("------------------------------------");
    }
}

//Singly Linked List to store and manage RideRequest details.
class SinglyLinkedList {

    // Node - the Inner Class
    private static class Node {
        RideRequest data;
        Node next;  

        Node(RideRequest data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head; 

    public SinglyLinkedList() {
        this.head = null; 
    }

    //Data Structure Methods

    public void insertAtEnd(RideRequest data) {
        Node node = new Node(data);
        if (head == null) {
            head = node; 
        } else {
            Node current = head;
            while (current.next != null) { 
                current = current.next;
            }
            current.next = node; 
        }
    }
    
     //Remove a ride request ID.
    
    public boolean deleteById(int rideId) {
        if (head == null) {
            return false;
        }

        if (head.data.getRideId() == rideId) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null && current.next.data.getRideId() != rideId) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            return true; 
        }

        return false; 
    }

   
     // Searches ride requests by ID.
    
    public RideRequest searchById(int rideId) {
        Node current = head;
        while (current != null) {
            if (current.data.getRideId() == rideId) {
                return current.data; 
            }
            current = current.next;
        }
        return null; 
    }

   
    public void displayAll() {
        if (head == null) {
            System.out.println("The list of ride requests is empty.");
            return;
        }

        System.out.println("\n All Ride Requests ");
        Node current = head;
        while (current != null) {
            current.data.display();
            current = current.next;
        }
    }

    // Sorting Algorithm

    public void sortRidesByFare() {
        if (head == null) {
            System.out.println("Cannot sort an empty list.");
            return;
        }

        // Linked List to Array
        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        RideRequest[] ridesArray = new RideRequest[size];
        current = head;
        for (int i = 0; i < size; i++) {
            ridesArray[i] = current.data;
            current = current.next;
        }

        //  Insertion Sort on the array
        for (int i = 1; i < size; i++) {
            RideRequest key = ridesArray[i];
            int j = i - 1;

            while (j >= 0 && ridesArray[j].getFare() > key.getFare()) {
                ridesArray[j + 1] = ridesArray[j];
                j = j - 1;
            }
            ridesArray[j + 1] = key;
        }

        // orted array
        System.out.println("\n Ride Requests Sorted by Fare (Low to High)");
        for (RideRequest ride : ridesArray) {
            ride.display();
        }
    }

    // Recursion
    public void printInReverse() {
        if (head == null) {
            System.out.println("The list is empty.");
            return;
        }
        System.out.println("\n Ride Requests in Reverse Order ");
        printReverseRecursive(head);
    }

    private void printReverseRecursive(Node node) {

        if (node == null) {
            return;
        }

        printReverseRecursive(node.next);
        node.data.display();
    }

    
     // calculate total fares of all rides using recursive function.
     
    public float calculateTotalFare() {
        return calculateTotalFareRecursive(head);
    }

    private float calculateTotalFareRecursive(Node node) {
        if (node == null) {
            return 0;
        }
        return node.data.getFare() + calculateTotalFareRecursive(node.next);
    }
}