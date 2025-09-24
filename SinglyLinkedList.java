
public class SinglyLinkedList {

    // --- Node Inner Class ---
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

    // --- Part B: Data Structure Methods ---

    public void insertAtEnd(RideRequest data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode; 
        } else {
            Node current = head;
            while (current.next != null) { 
                current = current.next;
            }
            current.next = newNode; 
        }
    }

    
     // Finds and removes a ride request by its unique ID.
    
    public boolean deleteById(int rideId) {
        if (head == null) return false; 
        
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
        }
        return false; 
    }

   
     // Searches for a ride request by its ID.
     
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
        System.out.println("\n--- All Ride Requests ---");
        Node current = head;
        while (current != null) {
            current.data.display();
            current = current.next;
        }
    }

    // --- Part C: Sorting Algorithm ---

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

        //  Perform Insertion Sort on the array
        for (int i = 1; i < size; i++) {
            RideRequest key = ridesArray[i];
            int j = i - 1;

            while (j >= 0 && ridesArray[j].getFare() > key.getFare()) {
                ridesArray[j + 1] = ridesArray[j];
                j = j - 1;
            }
            ridesArray[j + 1] = key;
        }

        // 3. sorted array
        System.out.println("\n--- Ride Requests Sorted by Fare (Low to High) ---");
        for (RideRequest ride : ridesArray) {
            ride.display();
        }
    }

    // --- Part D: Recursion ---

    public void printInReverse() {
        if (head == null) {
            System.out.println("The list is empty.");
            return;
        }
        System.out.println("\n--- Ride Requests in Reverse Order ---");
        printReverseRecursive(head);
    }

    private void printReverseRecursive(Node node) {

        if (node == null) {
            return;
        }

        printReverseRecursive(node.next);
        //Print the current node's data AFTER the recursive call has returned.
        node.data.display();
    }

    
     //  A recursive function to calculate the total fare of all rides.
     
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