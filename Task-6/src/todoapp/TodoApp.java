package todoapp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class TodoApp extends JFrame {

    private DefaultListModel<String> taskModel;
    private JList<String> taskList;
    private JTextField taskInput;

    public TodoApp() {
        setTitle("📝 My To-Do List");
        setSize(480, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 247, 250));

        // ── Title Label ──
        JLabel titleLabel = new JLabel("To-Do List", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(new Color(44, 62, 80));
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // ── Task List ──
        taskModel = new DefaultListModel<>();
        taskList = new JList<>(taskModel);
        taskList.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        taskList.setSelectionBackground(new Color(52, 152, 219));
        taskList.setSelectionForeground(Color.WHITE);
        taskList.setFixedCellHeight(36);
        taskList.setBorder(new EmptyBorder(4, 8, 4, 8));
        taskList.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // ── Bottom Panel (Input + Buttons) ──
        JPanel bottomPanel = new JPanel(new BorderLayout(8, 8));
        bottomPanel.setBackground(new Color(245, 247, 250));

        // Input row
        JPanel inputRow = new JPanel(new BorderLayout(8, 0));
        inputRow.setBackground(new Color(245, 247, 250));

        taskInput = new JTextField();
        taskInput.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        taskInput.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199)),
                new EmptyBorder(6, 10, 6, 10)));

        JButton addButton = createButton("➕ Add Task", new Color(39, 174, 96));

        inputRow.add(taskInput, BorderLayout.CENTER);
        inputRow.add(addButton, BorderLayout.EAST);

        // Button row
        JPanel buttonRow = new JPanel(new GridLayout(1, 3, 8, 0));
        buttonRow.setBackground(new Color(245, 247, 250));

        JButton markDoneButton  = createButton("✔ Mark Done",   new Color(52, 152, 219));
        JButton deleteButton    = createButton("🗑 Delete",      new Color(231, 76, 60));
        JButton clearAllButton  = createButton("🧹 Clear All",  new Color(149, 165, 166));

        buttonRow.add(markDoneButton);
        buttonRow.add(deleteButton);
        buttonRow.add(clearAllButton);

        bottomPanel.add(inputRow,   BorderLayout.NORTH);
        bottomPanel.add(buttonRow,  BorderLayout.SOUTH);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);

        // ── Status Bar ──
        JLabel statusBar = new JLabel("0 tasks", SwingConstants.RIGHT);
        statusBar.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        statusBar.setForeground(Color.GRAY);
        statusBar.setBorder(new EmptyBorder(4, 10, 4, 10));
        add(statusBar, BorderLayout.SOUTH);

        // ── Listeners ──

        // Add on button click
        addButton.addActionListener(e -> addTask(statusBar));

        // Add on Enter key
        taskInput.addActionListener(e -> addTask(statusBar));

        // Mark done (strikethrough via prefix)
        markDoneButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Please select a task to mark as done.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String task = taskModel.get(index);
            if (!task.startsWith("✔ ")) {
                taskModel.set(index, "✔ " + task);
            }
            updateStatus(statusBar);
        });

        // Delete selected task
        deleteButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Please select a task to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            taskModel.remove(index);
            updateStatus(statusBar);
        });

        // Clear all tasks
        clearAllButton.addActionListener(e -> {
            if (taskModel.isEmpty()) return;
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Clear all tasks?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                taskModel.clear();
                updateStatus(statusBar);
            }
        });
    }

    private void addTask(JLabel statusBar) {
        String text = taskInput.getText().trim();
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Task cannot be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        taskModel.addElement(text);
        taskInput.setText("");
        taskInput.requestFocus();
        updateStatus(statusBar);
    }

    private void updateStatus(JLabel statusBar) {
        int total = taskModel.size();
        long done = 0;
        for (int i = 0; i < total; i++) {
            if (taskModel.get(i).startsWith("✔ ")) done++;
        }
        statusBar.setText(total + " task(s)  |  " + done + " done   ");
    }

    private JButton createButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(8, 14, 8, 14));

        // Hover effect
        btn.addMouseListener(new MouseAdapter() {
            final Color original = bgColor;
            @Override public void mouseEntered(MouseEvent e) {
                btn.setBackground(original.darker());
            }
            @Override public void mouseExited(MouseEvent e) {
                btn.setBackground(original);
            }
        });
        return btn;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new TodoApp().setVisible(true));
    }
}
