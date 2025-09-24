
public class RideRequest {

    // --- Attributes ---
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

    // --- Getters and Setters ---

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

    // --- Display Method ---
   
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