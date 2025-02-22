import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Base class for a Hotel Room
class Room {
    protected int roomNumber;
    protected String roomType;
    protected boolean isBooked;
    protected double price;
    
    public Room(int roomNumber, String roomType, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isBooked = false;
        
    }
    
    public void bookRoom() {
        if (!isBooked) {
            isBooked = true;
            System.out.println("Room " + roomNumber + " booked successfully.");
        } else {
            System.out.println("Room " + roomNumber + " is already booked.");
        }
    }
    
    public void cancelBooking() {
        if (isBooked) {
            isBooked = false;
            System.out.println("Booking for Room " + roomNumber + " has been canceled.");
        } else {
            System.out.println("Room " + roomNumber + " was not booked.");
        }
    }
    
    public void displayRoomDetails() {
        System.out.println("Room Number: " + roomNumber + ", Type: " + roomType + ", Booked: " + isBooked + ", Price: $" + price);
    }
}

// Derived classes for different types of rooms
class DeluxeRoom extends Room {
    public DeluxeRoom(int roomNumber) {
        super(roomNumber, "Deluxe", 150.0);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom(int roomNumber) {
        super(roomNumber, "Suite", 250.0);
    }
}

class StandardRoom extends Room {
    public StandardRoom(int roomNumber) {
        super(roomNumber, "Standard", 100.0);
    }
}

class ExecutiveRoom extends Room {
    public ExecutiveRoom(int roomNumber) {
        super(roomNumber, "Executive", 200.0);
    }
}

// Customer class
class Customer {
    private String name;
    private int age;
    private ArrayList<Room> bookedRooms;
    
    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
        this.bookedRooms = new ArrayList<>();
    }
    
    public void bookRoom(Room room) {
        if (!room.isBooked) {
            room.bookRoom();
            bookedRooms.add(room);
        } else {
            System.out.println("Room is not available.");
        }
    }
    
    public void cancelBooking(Room room) {
        if (bookedRooms.contains(room)) {
            room.cancelBooking();
            bookedRooms.remove(room);
        } else {
            System.out.println("No booking found for this room.");
        }
    }
    
    public void displayCustomerDetails() {
        System.out.println("Customer Name: " + name + ", Age: " + age);
        if (!bookedRooms.isEmpty()) {
            System.out.println("Booked Rooms:");
            for (Room room : bookedRooms) {
                room.displayRoomDetails();
            }
        } else {
            System.out.println("No rooms booked yet.");
        }
    }
}

// Main class to test the system
public class HotelManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Room> rooms = new ArrayList<>();
        HashMap<String, Customer> customers = new HashMap<>();
        
        rooms.add(new DeluxeRoom(101));
        rooms.add(new SuiteRoom(102));
        rooms.add(new StandardRoom(103));
        rooms.add(new ExecutiveRoom(104));
        rooms.add(new DeluxeRoom(105));
        rooms.add(new SuiteRoom(106));
        
        while (true) {
            System.out.print("Enter Customer Name (or type 'exit' to quit): ");
            String name = scanner.next();
            if (name.equalsIgnoreCase("exit")) break;
            
            System.out.print("Enter Customer Age: ");
            int age = scanner.nextInt();
            
            Customer customer = customers.getOrDefault(name, new Customer(name, age));
            customers.put(name, customer);
            
            while (true) {
                System.out.println("Select an option: 1. Book Room 2. Cancel Booking 3. View Details 4. Logout");
                int option = scanner.nextInt();
                
                if (option == 1) {
                    System.out.println("Available Rooms:");
                    for (Room room : rooms) {
                        if (!room.isBooked) {
                            room.displayRoomDetails();
                        }
                    }
                    
                    System.out.print("Enter Room Number to Book: ");
                    int roomNumber = scanner.nextInt();
                    
                    for (Room room : rooms) {
                        if (room.roomNumber == roomNumber && !room.isBooked) {
                            customer.bookRoom(room);
                            break;
                        }
                    }
                } else if (option == 2) {
                    System.out.print("Enter Room Number to Cancel: ");
                    int roomNumber = scanner.nextInt();
                    
                    for (Room room : rooms) {
                        if (room.roomNumber == roomNumber) {
                            customer.cancelBooking(room);
                            break;
                        }
                    }
                } else if (option == 3) {
                    customer.displayCustomerDetails();
                } else if (option == 4) {
                    System.out.println("Logging out...");
                    break;
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
        
        scanner.close();
    }
}