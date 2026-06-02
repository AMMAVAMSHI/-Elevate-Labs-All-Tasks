# 📚 Library Management System
### A Mini Java OOP Project

---

## 🧠 OOP Concepts Demonstrated
| Concept | Where Used |
|---|---|
| **Encapsulation** | Private fields + getters/setters in `Book`, `User` |
| **Abstraction** | `Library` hides internal logic from `Main` |
| **Object Interaction** | `Library` orchestrates `Book` ↔ `User` relationships |
| **Collections** | `ArrayList` used for book catalogue and issued-book tracking |

---

## 📁 Project Structure
```
LibraryManagementSystem/
├── src/
│   ├── Book.java     ← Represents a book (id, title, author, availability)
│   ├── User.java     ← Represents a library member
│   ├── Library.java  ← Core logic: issue, return, display
│   └── Main.java     ← Entry point with interactive CLI menu
└── README.md
```

---

## ▶️ How to Run

### Using Terminal (javac + java)
```bash
# 1. Navigate to the src folder
cd LibraryManagementSystem/src

# 2. Compile all Java files
javac *.java

# 3. Run the program
java Main
```

### Using VS Code
1. Open the `LibraryManagementSystem` folder in VS Code
2. Install the **"Extension Pack for Java"** extension
3. Open `Main.java` and click the ▶️ **Run** button

---

## 🖥️ Sample Interaction
```
╔══════════════════════════════════════╗
║   Welcome to City Central Library   ║
╚══════════════════════════════════════╝
─────────────────────────────────────
 1. View All Books
 2. View Available Books
 3. View All Users & Issued Books
 4. Issue a Book
 5. Return a Book
 6. Exit
─────────────────────────────────────
Enter choice: 4

Enter User ID  : U001
Enter Book ID  : B003
✔  "1984" issued to Alice.
```

---

## 📋 Pre-loaded Test Data
| ID | Book Title | Author |
|---|---|---|
| B001 | The Great Gatsby | F. Scott Fitzgerald |
| B002 | To Kill a Mockingbird | Harper Lee |
| B003 | 1984 | George Orwell |
| B004 | Clean Code | Robert C. Martin |
| B005 | The Pragmatic Programmer | Andrew Hunt |

| ID | User |
|---|---|
| U001 | Alice |
| U002 | Bob |
| U003 | Charlie |

OUTPUT:
-------

![alt text](<WhatsApp Image 2026-06-02 at 9.38.26 PM.jpeg>)
![alt text](<WhatsApp Image 2026-06-02 at 9.38.41 PM.jpeg>)
