import java.util.*;

class Book {
    String title;
    boolean isAvailable = true;
    String borrowedBy = null;
    Date borrowDate = null;

    Book(String title) {
        this.title = title;
    }
}

public class AdvancedDigitalLibrary {
    static Scanner sc = new Scanner(System.in);
    static Map<String, Book> books = new LinkedHashMap<>();

    static String adminUser = "admin";
    static String adminPass = "admin123";
    static String userUser = "riddhi";
    static String userPass = "1234";

    static Map<String, List<String>> borrowedReports = new HashMap<>();

    public static void main(String[] args) {
        books.put("Java Programming", new Book("Java Programming"));
        books.put("Operating Systems", new Book("Operating Systems"));
        books.put("Data Structures", new Book("Data Structures"));
        books.put("DBMS", new Book("DBMS"));
        books.put("Web Development", new Book("Web Development"));

        System.out.println("📚✨ Welcome to Riddhi's Advanced Digital Library!");

        System.out.println("Login as: ");
        System.out.println("1️⃣ Admin");
        System.out.println("2️⃣ User");
        System.out.print("Choose: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            adminLogin();
        } else if (choice == 2) {
            userLogin();
        } else {
            System.out.println("❌ Invalid choice. Exiting...");
        }
    }

    private static void adminLogin() {
        System.out.print("Enter Admin username: ");
        String user = sc.nextLine();
        System.out.print("Enter Admin password: ");
        String pass = sc.nextLine();

        if (user.equals(adminUser) && pass.equals(adminPass)) {
            System.out.println("✅ Admin login successful!");
            adminMenu();
        } else {
            System.out.println("❌ Login failed. Exiting...");
        }
    }

    private static void adminMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n----- 🛡️ Admin Menu -----");
            System.out.println("1️⃣ Add Book");
            System.out.println("2️⃣ Delete Book");
            System.out.println("3️⃣ View All Books");
            System.out.println("4️⃣ View Borrowed Report");
            System.out.println("5️⃣ Logout");
            System.out.print("Choose: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Enter new book title: ");
                    String newBook = sc.nextLine();
                    if (!books.containsKey(newBook)) {
                        books.put(newBook, new Book(newBook));
                        System.out.println("✅ Book added successfully!");
                    } else {
                        System.out.println("⚠️ Book already exists!");
                    }
                    break;
                case 2:
                    System.out.print("Enter book title to delete: ");
                    String delBook = sc.nextLine();
                    if (books.containsKey(delBook)) {
                        books.remove(delBook);
                        System.out.println("✅ Book deleted!");
                    } else {
                        System.out.println("❌ Book not found!");
                    }
                    break;
                case 3:
                    displayBooks();
                    break;
                case 4:
                    showReports();
                    break;
                case 5:
                    exit = true;
                    System.out.println("👋 Admin logged out.");
                    break;
                default:
                    System.out.println("❌ Invalid option.");
            }
        }
    }

    private static void userLogin() {
        System.out.print("Enter User username: ");
        String user = sc.nextLine();
        System.out.print("Enter User password: ");
        String pass = sc.nextLine();

        if (user.equals(userUser) && pass.equals(userPass)) {
            System.out.println("✅ User login successful!");
            userMenu(user);
        } else {
            System.out.println("❌ Login failed. Exiting...");
        }
    }

    private static void userMenu(String username) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n----- 🙋 User Menu -----");
            System.out.println("1️⃣ View Books");
            System.out.println("2️⃣ Search Book");
            System.out.println("3️⃣ Borrow Book");
            System.out.println("4️⃣ Return Book");
            System.out.println("5️⃣ Logout");
            System.out.print("Choose: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    displayBooks();
                    break;
                case 2:
                    searchBook();
                    break;
                case 3:
                    borrowBook(username);
                    break;
                case 4:
                    returnBook(username);
                    break;
                case 5:
                    exit = true;
                    System.out.println("👋 User logged out.");
                    break;
                default:
                    System.out.println("❌ Invalid option.");
            }
        }
    }

    private static void displayBooks() {
        System.out.println("\n📖 All Books:");
        for (Book b : books.values()) {
            String status = b.isAvailable ? "Available ✅" : "Borrowed ❌ by " + b.borrowedBy;
            System.out.println("- " + b.title + " [" + status + "]");
        }
    }

    private static void searchBook() {
        System.out.print("Enter book title to search: ");
        String query = sc.nextLine();
        boolean found = false;
        for (Book b : books.values()) {
            if (b.title.toLowerCase().contains(query.toLowerCase())) {
                String status = b.isAvailable ? "Available ✅" : "Borrowed ❌ by " + b.borrowedBy;
                System.out.println("- " + b.title + " [" + status + "]");
                found = true;
            }
        }
        if (!found) {
            System.out.println("❌ No matching book found!");
        }
    }

    private static void borrowBook(String username) {
        System.out.print("Enter book title to borrow: ");
        String bookTitle = sc.nextLine();
        Book book = books.get(bookTitle);

        if (book != null && book.isAvailable) {
            book.isAvailable = false;
            book.borrowedBy = username;
            book.borrowDate = new Date();

            borrowedReports.putIfAbsent(username, new ArrayList<>());
            borrowedReports.get(username).add(bookTitle);

            System.out.println("✅ You borrowed '" + bookTitle + "'. Please return within 7 days!");
        } else {
            System.out.println("❌ Book not available or not found!");
        }
    }

    private static void returnBook(String username) {
        System.out.print("Enter book title to return: ");
        String bookTitle = sc.nextLine();
        Book book = books.get(bookTitle);

        if (book != null && username.equals(book.borrowedBy)) {
            long diff = (new Date().getTime() - book.borrowDate.getTime()) / (1000 * 60 * 60 * 24);
            if (diff > 7) {
                int fine = (int)(diff - 7) * 10;
                System.out.println("⚠️ You are late by " + (diff - 7) + " days. Fine: ₹" + fine);
            } else {
                System.out.println("✅ Book returned on time. Thank you!");
            }
            book.isAvailable = true;
            book.borrowedBy = null;
            book.borrowDate = null;
        } else {
            System.out.println("❌ You did not borrow this book!");
        }
    }

    private static void showReports() {
        System.out.println("\n📄 Borrowed Books Report:");
        if (borrowedReports.isEmpty()) {
            System.out.println("No borrowed books currently.");
        } else {
            for (Map.Entry<String, List<String>> entry : borrowedReports.entrySet()) {
                System.out.println("- User: " + entry.getKey() + ", Books: " + entry.getValue());
            }
        }
    }
}
