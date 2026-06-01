import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class StudentRecordSystem {

    private static final String SEPARATOR = "+-------+----------------------+---------+-------+";
    private static final String HEADER    = "| ID    | Name                 | Marks   | Grade |";

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        printBanner();

        while (running) {
            printMenu();
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> addStudent(manager, scanner);
                case "2" -> viewAllStudents(manager);
                case "3" -> searchStudent(manager, scanner);
                case "4" -> updateStudent(manager, scanner);
                case "5" -> deleteStudent(manager, scanner);
                case "6" -> showStatistics(manager);
                case "0" -> {
                    System.out.println("\n  Goodbye! All records cleared on exit.\n");
                    running = false;
                }
                default  -> System.out.println("  [!] Invalid choice. Please enter 0-6.\n");
            }
        }

        scanner.close();
    }

    // ─── BANNER ────────────────────────────────────────────────────────────────

    private static void printBanner() {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════════╗");
        System.out.println("  ║     STUDENT RECORD MANAGEMENT SYSTEM     ║");
        System.out.println("  ║          CLI-Based CRUD Application      ║");
        System.out.println("  ╚══════════════════════════════════════════╝");
        System.out.println("  3 sample records loaded.\n");
    }

    // ─── MENU ──────────────────────────────────────────────────────────────────

    private static void printMenu() {
        System.out.println("  ┌─────────────────────────────┐");
        System.out.println("  │         MAIN MENU           │");
        System.out.println("  ├─────────────────────────────┤");
        System.out.println("  │  1. Add Student             │");
        System.out.println("  │  2. View All Students       │");
        System.out.println("  │  3. Search Student by ID   │");
        System.out.println("  │  4. Update Student          │");
        System.out.println("  │  5. Delete Student          │");
        System.out.println("  │  6. Show Statistics         │");
        System.out.println("  │  0. Exit                    │");
        System.out.println("  └─────────────────────────────┘");
    }

    // ─── 1. ADD ────────────────────────────────────────────────────────────────

    private static void addStudent(StudentManager manager, Scanner scanner) {
        System.out.println("\n  ── Add New Student ──");
        System.out.print("  Enter name  : ");
        String name = scanner.nextLine().trim();

        double marks = promptMarks(scanner);
        if (marks < 0) return; // invalid input

        try {
            Student s = manager.addStudent(name, marks);
            System.out.printf("  [✓] Student added! ID = %d, Grade = %s%n%n", s.getId(), s.getGrade());
        } catch (IllegalArgumentException e) {
            System.out.println("  [!] " + e.getMessage() + "\n");
        }
    }

    // ─── 2. VIEW ALL ───────────────────────────────────────────────────────────

    private static void viewAllStudents(StudentManager manager) {
        System.out.println("\n  ── All Students ──");
        List<Student> list = manager.getAllStudents();

        if (list.isEmpty()) {
            System.out.println("  No records found.\n");
            return;
        }

        printTableHeader();
        for (Student s : list) System.out.println("  " + s);
        System.out.println("  " + SEPARATOR);
        System.out.printf("  Total: %d student(s)%n%n", list.size());
    }

    // ─── 3. SEARCH ─────────────────────────────────────────────────────────────

    private static void searchStudent(StudentManager manager, Scanner scanner) {
        System.out.println("\n  ── Search Student ──");
        int id = promptId(scanner);
        if (id < 0) return;

        Optional<Student> opt = manager.findById(id);
        if (opt.isPresent()) {
            printTableHeader();
            System.out.println("  " + opt.get());
            System.out.println("  " + SEPARATOR + "\n");
        } else {
            System.out.printf("  [!] No student found with ID %d.%n%n", id);
        }
    }

    // ─── 4. UPDATE ─────────────────────────────────────────────────────────────

    private static void updateStudent(StudentManager manager, Scanner scanner) {
        System.out.println("\n  ── Update Student ──");
        int id = promptId(scanner);
        if (id < 0) return;

        Optional<Student> opt = manager.findById(id);
        if (opt.isEmpty()) {
            System.out.printf("  [!] No student found with ID %d.%n%n", id);
            return;
        }

        Student current = opt.get();
        System.out.printf("  Current name  : %s%n", current.getName());
        System.out.print("  New name (Enter to keep): ");
        String newName = scanner.nextLine().trim();
        if (newName.isEmpty()) newName = current.getName();

        System.out.printf("  Current marks : %.2f%n", current.getMarks());
        double newMarks = promptMarks(scanner);
        if (newMarks < 0) newMarks = current.getMarks();

        manager.updateStudent(id, newName, newMarks);
        System.out.println("  [✓] Student updated successfully.\n");
    }

    // ─── 5. DELETE ─────────────────────────────────────────────────────────────

    private static void deleteStudent(StudentManager manager, Scanner scanner) {
        System.out.println("\n  ── Delete Student ──");
        int id = promptId(scanner);
        if (id < 0) return;

        Optional<Student> opt = manager.findById(id);
        if (opt.isEmpty()) {
            System.out.printf("  [!] No student found with ID %d.%n%n", id);
            return;
        }

        System.out.printf("  Are you sure you want to delete \"%s\"? (y/n): ", opt.get().getName());
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (confirm.equals("y") || confirm.equals("yes")) {
            manager.deleteStudent(id);
            System.out.println("  [✓] Student deleted.\n");
        } else {
            System.out.println("  Cancelled.\n");
        }
    }

    // ─── 6. STATISTICS ─────────────────────────────────────────────────────────

    private static void showStatistics(StudentManager manager) {
        System.out.println("\n  ── Class Statistics ──");
        if (manager.getTotalStudents() == 0) {
            System.out.println("  No records available.\n");
            return;
        }
        System.out.printf("  Total Students : %d%n", manager.getTotalStudents());
        System.out.printf("  Class Average  : %.2f%n", manager.getClassAverage());
        Student top = manager.getTopStudent();
        if (top != null) {
            System.out.printf("  Top Student    : %s (%.2f — %s)%n%n",
                    top.getName(), top.getMarks(), top.getGrade());
        }
    }

    // ─── HELPERS ───────────────────────────────────────────────────────────────

    private static void printTableHeader() {
        System.out.println("  " + SEPARATOR);
        System.out.println("  " + HEADER);
        System.out.println("  " + SEPARATOR);
    }

    private static int promptId(Scanner scanner) {
        System.out.print("  Enter student ID: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("  [!] Invalid ID. Please enter a number.\n");
            return -1;
        }
    }

    private static double promptMarks(Scanner scanner) {
        System.out.print("  Enter marks (0–100): ");
        try {
            double m = Double.parseDouble(scanner.nextLine().trim());
            if (m < 0 || m > 100) {
                System.out.println("  [!] Marks out of range.\n");
                return -1;
            }
            return m;
        } catch (NumberFormatException e) {
            System.out.println("  [!] Invalid marks value.\n");
            return -1;
        }
    }
}
