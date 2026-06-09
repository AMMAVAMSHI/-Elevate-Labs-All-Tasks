# 📝 Java Swing – To-Do App

A simple desktop To-Do List application built with Java Swing.

---

## 📁 Project Structure

```
TodoApp/
├── src/
│   └── todoapp/
│       └── TodoApp.java      ← Main source file
├── out/                      ← Compiled .class files (after build)
└── README.md
```

---

## ✅ Features

| Feature | Description |
|---|---|
| ➕ Add Task | Type a task and click **Add Task** or press **Enter** |
| ✔ Mark Done | Select a task and click **Mark Done** (prefixes with ✔) |
| 🗑 Delete | Select a task and click **Delete** |
| 🧹 Clear All | Remove all tasks at once (with confirmation) |
| 📊 Status Bar | Shows total tasks and how many are done |

---

## 🚀 How to Run

### Option 1 – IntelliJ IDEA (Recommended)
1. Open IntelliJ → **File → Open** → select the `TodoApp` folder
2. Right-click `TodoApp.java` → **Run 'TodoApp.main()'**

### Option 2 – Eclipse
1. **File → New → Java Project** → name it `TodoApp`
2. Copy `TodoApp.java` into `src/todoapp/`
3. Right-click the file → **Run As → Java Application**

### Option 3 – Command Line
```bash
# Compile
javac -d out src/todoapp/TodoApp.java

# Run
java -cp out todoapp.TodoApp
```

---

## 🛠 Requirements

- Java JDK 8 or higher (Swing is built-in – no external libraries needed)

---

## 🎓 Concepts Covered

- `JFrame` – main application window
- `JTextField` – text input for new tasks
- `JButton` – Add / Delete / Mark Done / Clear All
- `JList` + `DefaultListModel` – dynamic task list
- `JScrollPane` – scrollable list
- `ActionListener` – button click & Enter key events
- `JOptionPane` – confirmation & warning dialogs
