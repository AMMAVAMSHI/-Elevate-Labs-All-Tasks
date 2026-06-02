import java.util.Scanner;

/**
 * Library Management System — Main Entry Point
 * Demonstrates OOP concepts: Encapsulation, Abstraction, and Object Interaction.
 */
public class Main {

    public static void main(String[] args) {

        // ── Seed data ─────────────────────────────────────────────────────────
        Library library = new Library("City Central Library");

        library.addBook(new Book("B001", "The Great Gatsby",         "F. Scott Fitzgerald"));
        library.addBook(new Book("B002", "To Kill a Mockingbird",    "Harper Lee"));
        library.addBook(new Book("B003", "1984",                     "George Orwell"));
        library.addBook(new Book("B004", "Clean Code",               "Robert C. Martin"));
        library.addBook(new Book("B005", "The Pragmatic Programmer", "Andrew Hunt"));

        library.addUser(new User("U001", "Alice"));
        library.addUser(new User("U002", "Bob"));
        library.addUser(new User("U003", "Charlie"));

        // ── Interactive menu ──────────────────────────────────────────────────
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║   Welcome to City Central Library    ║");
        System.out.println("╚══════════════════════════════════════╝");

        while (running) {
            printMenu();
            System.out.print("Enter choice: ");
            String choice = sc.nextLine().trim();
            System.out.println();

            switch (choice) {
                case "1":
                    library.showAllBooks();
                    break;

                case "2":
                    library.showAvailableBooks();
                    break;

                case "3":
                    library.showAllUsers();
                    break;

                case "4":
                    System.out.print("Enter User ID  : "); String uid = sc.nextLine().trim();
                    System.out.print("Enter Book ID  : "); String bid = sc.nextLine().trim();
                    library.issueBook(uid, bid);
                    break;

                case "5":
                    System.out.print("Enter User ID  : "); String ruid = sc.nextLine().trim();
                    System.out.print("Enter Book ID  : "); String rbid = sc.nextLine().trim();
                    library.returnBook(ruid, rbid);
                    break;

                case "6":
                    System.out.println("👋  Thank you for using the Library System. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("⚠  Invalid option. Please choose 1–6.");
            }
            System.out.println();
        }

        sc.close();
    }

    private static void printMenu() {
        System.out.println("─────────────────────────────────────");
        System.out.println(" 1. View All Books");
        System.out.println(" 2. View Available Books");
        System.out.println(" 3. View All Users & Issued Books");
        System.out.println(" 4. Issue a Book");
        System.out.println(" 5. Return a Book");
        System.out.println(" 6. Exit");
        System.out.println("─────────────────────────────────────");
    }
}
