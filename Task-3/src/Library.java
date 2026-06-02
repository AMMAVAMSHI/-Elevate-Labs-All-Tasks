import java.util.ArrayList;
import java.util.List;

public class Library {
    private String libraryName;
    private List<Book> books;
    private List<User> users;

    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    // ── Catalogue management ─────────────────────────────────────────────────

    public void addBook(Book book) {
        books.add(book);
        System.out.println("✔  Book added: " + book);
    }

    public void addUser(User user) {
        users.add(user);
        System.out.println("✔  User registered: " + user);
    }

    // ── Core features ─────────────────────────────────────────────────────────

    public void issueBook(String userId, String bookId) {
        User user = findUser(userId);
        Book book = findBook(bookId);

        if (user == null) {
            System.out.println("✘  User ID not found: " + userId);
            return;
        }
        if (book == null) {
            System.out.println("✘  Book ID not found: " + bookId);
            return;
        }
        if (!book.isAvailable()) {
            System.out.println("✘  Book \"" + book.getTitle() + "\" is currently issued to someone else.");
            return;
        }

        book.setAvailable(false);
        user.borrowBook(book);
        System.out.println("✔  \"" + book.getTitle() + "\" issued to " + user.getName() + ".");
    }

    public void returnBook(String userId, String bookId) {
        User user = findUser(userId);
        Book book = findBook(bookId);

        if (user == null) {
            System.out.println("✘  User ID not found: " + userId);
            return;
        }
        if (book == null) {
            System.out.println("✘  Book ID not found: " + bookId);
            return;
        }
        if (!user.getIssuedBooks().contains(book)) {
            System.out.println("✘  This book was not issued to " + user.getName() + ".");
            return;
        }

        book.setAvailable(true);
        user.returnBook(book);
        System.out.println("✔  \"" + book.getTitle() + "\" returned by " + user.getName() + ". Thank you!");
    }

    // ── Display helpers ───────────────────────────────────────────────────────

    public void showAllBooks() {
        System.out.println("\n📚  All Books in " + libraryName + ":");
        if (books.isEmpty()) { System.out.println("  (no books)"); return; }
        for (Book b : books) System.out.println("  " + b);
    }

    public void showAvailableBooks() {
        System.out.println("\n✅  Available Books:");
        boolean found = false;
        for (Book b : books) {
            if (b.isAvailable()) { System.out.println("  " + b); found = true; }
        }
        if (!found) System.out.println("  (none available right now)");
    }

    public void showAllUsers() {
        System.out.println("\n👤  Registered Users:");
        if (users.isEmpty()) { System.out.println("  (no users)"); return; }
        for (User u : users) {
            System.out.println("  " + u);
            u.showIssuedBooks();
        }
    }

    // ── Private helpers ───────────────────────────────────────────────────────

    private Book findBook(String bookId) {
        for (Book b : books) if (b.getBookId().equalsIgnoreCase(bookId)) return b;
        return null;
    }

    private User findUser(String userId) {
        for (User u : users) if (u.getUserId().equalsIgnoreCase(userId)) return u;
        return null;
    }
}
