import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String loginId;
    private String password;
    public User(String loginId, String password) {
            this.loginId = loginId;
            this.password = password;
             }
    public String getLoginId() {
            return loginId;
         }
    public String getPassword() {
            return password;
        }
    }
class ReservationSystem {
    private Map<String, Boolean> reservations;
    private Map<String, String> pnrNumbers;
    public ReservationSystem() {
        reservations = new HashMap<>();
        pnrNumbers = new HashMap<>();
        }
    public void makeReservation(String name, String trainNumber, String classType, String dateOfJourney, String from, String to) {
        if (reservations.containsKey(name)) {
            System.out.println("Sorry, the reservation for " + name + " already exists.");
                } else {
                        reservations.put(name, true);
                        String pnrNumber = generatePNR();
                        pnrNumbers.put(pnrNumber, name);
                        System.out.println("\n**\u001B[1mReservation for " + name + " has been successfully made.**\n" + "\u001B[0m" );
                        System.out.println("PNR Number: " + pnrNumber);
                        }
                }
    public void cancelReservation(String pnrNumber) {
        String name = pnrNumbers.get(pnrNumber);
            if (name != null) {
                reservations.remove(name);
                pnrNumbers.remove(pnrNumber);
                System.out.println("\n**\u001B[1m Reservation for " + name + " with PNR Number " + pnrNumber + " has been successfully canceled**." + "\u001B[0m" );
                } else {
                    System.out.println("Sorry, no reservation found for PNR Number " + pnrNumber + ".");
                        }
        }
    private String generatePNR() { // Generate a random PNR number
        return String.valueOf((int) (Math.random() * 900000) + 100000);
        }
}
public class Online_ReservationSystem {
    public static void main(String[] args) {
        ReservationSystem reservationSystem = new ReservationSystem();
            Scanner scanner = new Scanner(System.in);
        // Login Form
        System.out.println("\u001B[1mLogin Form" + "\u001B[0m" );
        System.out.print("\u001B[1mEnter login ID: " + "\u001B[0m" );
            String loginId = scanner.nextLine();
        System.out.print("\u001B[1mEnter password: " + "\u001B[0m" );
                String password = scanner.nextLine();
                User user = new User(loginId, password);
        // Authentication
        if (!authenticateUser(user)) {
            System.out.println("Invalid login credentials. Exiting...");
            scanner.close();//Close the scanner before exiting
            System.exit(0);
        }
        while (true) {
            System.out.println("\n\u001B[1m1. Reservation Form" + "\u001B[0m" );
            System.out.println("\u001B[1m2. Cancellation Form" + "\u001B[0m" );
            System.out.println("\u001B[1m3. Exit"+ "\u001B[0m" );
            System.out.print("\u001B[1mEnter your choice: "+ "\u001B[0m" );
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (choice) {
                case 1:
                    System.out.println("\n\u001B[1mReservation Form"+ "\u001B[0m" );
                    System.out.print("\u001B[1mEnter your name: "+ "\u001B[0m" );
                        String name = scanner.nextLine();
                    System.out.print("\u001B[1mEnter train number: "+ "\u001B[0m" );
                        String trainNumber = scanner.nextLine();  // Assuming train name will be automatically fetched based on train number
                    System.out.print("\u001B[1mEnter class type: "+ "\u001B[0m" );
                        String classType = scanner.nextLine();
                    System.out.print("\u001B[1mEnter date of journey: "+ "\u001B[0m" );
                        String dateOfJourney = scanner.nextLine();
                    System.out.print("\u001B[1mEnter from (place): "+ "\u001B[0m" );                                                       
                        String from = scanner.nextLine();
                    System.out.print("\u001B[1mEnter destination: "+ "\u001B[0m" );                                                                    
                        String to = scanner.nextLine();                                                                                         
reservationSystem.makeReservation(name, trainNumber, classType, dateOfJourney, from, to);
                    // Display reservation details in tabular format
                    System.out.println("\n\u001B[1mReservation Details" + "\u001B[0m");
                    System.out.println("-------------------------------------");
                    System.out.printf("| %-15s | %-14s |\n", "Name"  , name);
                     System.out.println("-------------------------------------");
                    System.out.printf("| %-15s | %-14s |\n", "Train Number" , trainNumber);                    System.out.println("-------------------------------------");
                    System.out.println("-------------------------------------");
                    System.out.printf("| %-15s | %-14s |\n", "Class Type", classType);                    System.out.println("-------------------------------------");
                    System.out.println("-------------------------------------");
                    System.out.printf("| %-15s | %-14s |\n", "ate of Journey" , dateOfJourney);
                    System.out.println("-------------------------------------");
                    System.out.printf("| %-15s | %-14s |\n", "From" , from);
                    System.out.println("-------------------------------------");
                    System.out.printf("| %-15s | %-14s |\n", "Destination" , to);
                    System.out.println("-------------------------------------");

                break;
                case 2:
                System.out.println("\nCancellaton Form");
                System.out.println("Enter PNR number:");
                    String pnrNumber = scanner.nextLine();
reservationSystem.cancelReservation(pnrNumber);
                break;
                case 3:
                    System.out.println("Exiting...");
                     scanner.close();//Close the scanner before exiting
                return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                }   
                     
        }             
    } 
     private static boolean authenticateUser(User user) {
    // Perform authentication logic here
    // For simplicity, let's assume a hardcoded valid login ID and password
        String validLoginId = "Yashashri";
        String validPassword = "password123";
        return user.getLoginId().equals(validLoginId) && user.getPassword().equals(validPassword);
        }
}



