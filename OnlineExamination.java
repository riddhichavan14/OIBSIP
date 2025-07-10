import java.util.*;

public class OnlineExamination {
    private static String username = "riddhi";
    private static String password = "1234";
    private static Scanner sc = new Scanner(System.in);
    private static boolean loggedIn = false;

    public static void main(String[] args) {
        System.out.println("🎓 Welcome to Riddhi's Online Examination System!");

        login();

        if (loggedIn) {
            boolean exit = false;
            while (!exit) {
                System.out.println("\n----- Menu -----");
                System.out.println("1️⃣ Update Profile");
                System.out.println("2️⃣ Start Exam");
                System.out.println("3️⃣ Logout");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();
                sc.nextLine(); // buffer clear

                switch (choice) {
                    case 1:
                        updateProfile();
                        break;
                    case 2:
                        startExam();
                        break;
                    case 3:
                        exit = true;
                        logout();
                        break;
                    default:
                        System.out.println("❌ Invalid option. Try again!");
                }
            }
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        if (user.equals(username) && pass.equals(password)) {
            loggedIn = true;
            System.out.println("✅ Login successful!");
        } else {
            System.out.println("❌ Login failed. Exiting...");
            System.exit(0);
        }
    }

    private static void updateProfile() {
        System.out.print("Enter new username: ");
        username = sc.nextLine();
        System.out.print("Enter new password: ");
        password = sc.nextLine();
        System.out.println("✅ Profile updated successfully!");
    }

    private static void startExam() {
        System.out.println("\n⏰ Exam started! You have 30 seconds to complete.");

        Map<String, String> questions = new LinkedHashMap<>();
        questions.put("Java is a ______ language. (a) Low level (b) High level (c) Assembly (d) Machine", "b");
        questions.put("Which keyword is used to create an object in Java? (a) class (b) object (c) new (d) create", "c");
        questions.put("Which method is the entry point in Java? (a) start() (b) main() (c) run() (d) execute()", "b");
        questions.put("Java is developed by? (a) Apple (b) Microsoft (c) Sun Microsystems (d) IBM", "c");
        questions.put("Which one is not an OOP concept? (a) Encapsulation (b) Inheritance (c) Compilation (d) Polymorphism", "c");

        int score = 0;
        long startTime = System.currentTimeMillis();

        for (Map.Entry<String, String> entry : questions.entrySet()) {
            System.out.println("\n" + entry.getKey());
            System.out.print("Your answer: ");
            String ans = sc.nextLine();

            long currentTime = System.currentTimeMillis();
            if ((currentTime - startTime) / 1000 > 30) {
                System.out.println("\n⏰ Time up! Auto-submitting your exam...");
                break;
            }

            if (ans.equalsIgnoreCase(entry.getValue())) {
                score++;
            }
        }

        System.out.println("\n🎯 Exam finished!");
        System.out.println("✅ Your score: " + score + "/" + questions.size());
    }

    private static void logout() {
        System.out.println("🙏 You have been logged out successfully. Thank you!");
        loggedIn = false;
    }
}
