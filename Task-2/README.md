# Student Record Management System
## CLI-Based CRUD Application in Java

---

## Files Included
| File | Purpose |
|------|---------|
| `Student.java` | Model class — fields: id, name, marks, grade |
| `StudentManager.java` | Business logic — ArrayList CRUD operations |
| `StudentRecordSystem.java` | Main class — CLI menu and user interaction |

---

## How to Compile & Run

### Option A — Command Line (any OS)
```bash
# 1. Open a terminal in the folder containing all .java files

# 2. Compile all three files
javac Student.java StudentManager.java StudentRecordSystem.java

# 3. Run the program
java StudentRecordSystem
```

### Option B — VS Code
1. Install the **Extension Pack for Java** from the marketplace.
2. Open the folder containing the `.java` files.
3. Open `StudentRecordSystem.java`.
4. Click **Run** (▶) at the top-right, or press `F5`.

### Option C — IntelliJ IDEA (Community Edition)
1. **File → New Project → Java** (no build system needed).
2. Copy all three `.java` files into `src/`.
3. Right-click `StudentRecordSystem.java` → **Run 'StudentRecordSystem.main()'**.

---

## Menu Options
```
1. Add Student      — Enter name and marks; auto-assigns ID + grade
2. View All         — Tabular view of all records
3. Search by ID     — Look up a single student
4. Update           — Edit name and/or marks of existing student
5. Delete           — Remove a student (with confirmation prompt)
6. Statistics       — Total count, class average, top student
0. Exit
```

## Grading Scale
| Marks  | Grade |
|--------|-------|
| 90–100 | A+    |
| 80–89  | A     |
| 70–79  | B     |
| 60–69  | C     |
| 50–59  | D     |
| 0–49   | F     |

---

## Requirements
- Java 17 or later (uses switch expressions & `var`)
- No external libraries — pure standard Java

  -Outputs
  <img width="741" height="882" alt="image" src="https://github.com/user-attachments/assets/027b6b33-4cd8-4e8d-ad7a-905cf219dd38" />
  <img width="710" height="896" alt="image" src="https://github.com/user-attachments/assets/266355fe-b3d1-454c-9dc0-d1628d8c30d6" />
  <img width="612" height="848" alt="image" src="https://github.com/user-attachments/assets/020fbccc-4422-4e9b-9001-6c1dfa52c4bf" />

  

  

