import java.util.Scanner;

public class OnlineReservationSystem {
    static String reservedName = "";
    static String reservedTrain = "";
    static String reservedFrom = "";
    static String reservedTo = "";
    static String reservedDate = "";
    static String reservedPNR = "";
    static boolean isReserved = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Login
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        if (user.equals("user") && pass.equals("pass")) {
            System.out.println("\nLogin successful!\n");
            boolean exit = false;

            while (!exit) {
                System.out.println("1. Reserve Ticket");
                System.out.println("2. Cancel Ticket");
                System.out.println("3. Exit");
                System.out.print("Choose option: ");
                int option = sc.nextInt();
                sc.nextLine(); // Clear buffer

                switch (option) {
                    case 1:
                        if (isReserved) {
                            System.out.println("Ticket already reserved. Please cancel first.");
                            break;
                        }
                        System.out.print("Enter Name: ");
                        reservedName = sc.nextLine();
                        System.out.print("Enter Train Number: ");
                        reservedTrain = sc.nextLine();
                        System.out.print("From: ");
                        reservedFrom = sc.nextLine();
                        System.out.print("To: ");
                        reservedTo = sc.nextLine();
                        System.out.print("Journey Date: ");
                        reservedDate = sc.nextLine();
                        reservedPNR = "PNR" + (int)(Math.random() * 10000);
                        isReserved = true;
                        System.out.println("Reservation successful! Your PNR: " + reservedPNR);
                        break;
                    case 2:
                        if (!isReserved) {
                            System.out.println("No reservation found to cancel.");
                            break;
                        }
                        System.out.print("Enter PNR number to cancel: ");
                        String pnrInput = sc.nextLine();
                        if (pnrInput.equals(reservedPNR)) {
                            System.out.println("Reservation found for: " + reservedName);
                            System.out.print("Confirm cancellation (yes/no): ");
                            String confirm = sc.nextLine();
                            if (confirm.equalsIgnoreCase("yes")) {
                                isReserved = false;
                                System.out.println("Ticket cancelled successfully.");
                            } else {
                                System.out.println("Cancellation aborted.");
                            }
                        } else {
                            System.out.println("PNR not found!");
                        }
                        break;
                    case 3:
                        exit = true;
                        System.out.println("Thank you for using Reservation System!");
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
        } else {
            System.out.println("Login failed! Invalid credentials.");
        }

        sc.close();
    }
}
