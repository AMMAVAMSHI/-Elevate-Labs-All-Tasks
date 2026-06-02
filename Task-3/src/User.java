import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String name;
    private List<Book> issuedBooks;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.issuedBooks = new ArrayList<>();
    }

    // Getters
    public String getUserId()         { return userId; }
    public String getName()           { return name; }
    public List<Book> getIssuedBooks() { return issuedBooks; }

    public void borrowBook(Book book) {
        issuedBooks.add(book);
    }

    public void returnBook(Book book) {
        issuedBooks.remove(book);
    }

    public void showIssuedBooks() {
        if (issuedBooks.isEmpty()) {
            System.out.println("  No books currently issued to " + name + ".");
        } else {
            System.out.println("  Books issued to " + name + ":");
            for (Book b : issuedBooks) {
                System.out.println("    - " + b);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("User[%s]: %s (Books Issued: %d)", userId, name, issuedBooks.size());
    }
}
