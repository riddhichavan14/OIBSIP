import java.util.ArrayList;
import java.util.Scanner;

public class ATMInterface {
    private static double balance = 10000.0; // Starting balance
    private static ArrayList<String> miniStatement = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pin = 1234;

        System.out.print("Enter your ATM PIN: ");
        int enteredPin = sc.nextInt();

        if (enteredPin == pin) {
            System.out.println("\n✅ Login successful! Welcome to Riddhi's ATM 💳");
            boolean exit = false;

            while (!exit) {
                System.out.println("\n----- ATM Menu -----");
                System.out.println("1️⃣  Check Balance");
                System.out.println("2️⃣  Deposit Money");
                System.out.println("3️⃣  Withdraw Money");
                System.out.println("4️⃣  View Mini Statement");
                System.out.println("5️⃣  Exit");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("💰 Your current balance is: ₹" + balance);
                        break;

                    case 2:
                        System.out.print("Enter amount to deposit: ₹");
                        double depositAmount = sc.nextDouble();
                        if (depositAmount > 0) {
                            balance += depositAmount;
                            miniStatement.add("Deposited: ₹" + depositAmount);
                            System.out.println("✅ ₹" + depositAmount + " deposited successfully!");
                        } else {
                            System.out.println("❌ Invalid amount.");
                        }
                        break;

                    case 3:
                        System.out.print("Enter amount to withdraw: ₹");
                        double withdrawAmount = sc.nextDouble();
                        if (withdrawAmount > 0 && withdrawAmount <= balance) {
                            balance -= withdrawAmount;
                            miniStatement.add("Withdrawn: ₹" + withdrawAmount);
                            System.out.println("✅ ₹" + withdrawAmount + " withdrawn successfully!");
                        } else {
                            System.out.println("❌ Insufficient balance or invalid amount.");
                        }
                        break;

                    case 4:
                        System.out.println("\n----- Mini Statement -----");
                        if (miniStatement.isEmpty()) {
                            System.out.println("No transactions yet.");
                        } else {
                            for (String transaction : miniStatement) {
                                System.out.println(transaction);
                            }
                        }
                        break;

                    case 5:
                        exit = true;
                        System.out.println("🙏 Thank you for using Riddhi's ATM. Goodbye!");
                        break;

                    default:
                        System.out.println("❌ Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("❌ Incorrect PIN! Access denied.");
        }

        sc.close();
    }
}
