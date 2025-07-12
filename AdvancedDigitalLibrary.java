import java.util.ArrayList;
import java.util.Scanner;

public class Book {  // ← public class Book kar diya
    String title;
    boolean isIssued;

    Book(String title) {
        this.title = title;
        this.isIssued = false;
    }

    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        books.add(new Book("Java Programming"));
        books.add(new Book("Data Structures"));
        books.add(new Book("Web Development"));

        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Digital Library Menu ---");
            System.out.println("1. View Books");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Add Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Books:");
                    for (Book b : books) {
                        String status = b.isIssued ? "Issued ❌" : "Available ✅";
                        System.out.println("- " + b.title + " [" + status + "]");
                    }
                    break;
                case 2:
                    System.out.print("Enter book title to issue: ");
                    String issueTitle = sc.nextLine();
                    boolean foundIssue = false;
                    for (Book b : books) {
                        if (b.title.equalsIgnoreCase(issueTitle)) {
                            if (!b.isIssued) {
                                b.isIssued = true;
                                System.out.println("✅ Book issued successfully!");
                            } else {
                                System.out.println("❌ Book already issued!");
                            }
                            foundIssue = true;
                            break;
                        }
                    }
                    if (!foundIssue) {
                        System.out.println("❌ Book not found!");
                    }
                    break;
                case 3:
                    System.out.print("Enter book title to return: ");
                    String returnTitle = sc.nextLine();
                    boolean foundReturn = false;
                    for (Book b : books) {
                        if (b.title.equalsIgnoreCase(returnTitle)) {
                            if (b.isIssued) {
                                b.isIssued = false;
                                System.out.println("✅ Book returned successfully!");
                            } else {
                                System.out.println("⚠️ This book was not issued!");
                            }
                            foundReturn = true;
                            break;
                        }
                    }
                    if (!foundReturn) {
                        System.out.println("❌ Book not found!");
                    }
                    break;
                case 4:
                    System.out.print("Enter new book title: ");
                    String newBook = sc.nextLine();
                    books.add(new Book(newBook));
                    System.out.println("✅ Book added successfully!");
                    break;
                case 5:
                    exit = true;
                    System.out.println("Thank you! Exiting... 👋");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
        sc.close();
    }
}