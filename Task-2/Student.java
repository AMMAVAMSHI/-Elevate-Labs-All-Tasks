public class Student {
    private int id;
    private String name;
    private double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getMarks() { return marks; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setMarks(double marks) { this.marks = marks; }

    public String getGrade() {
        if (marks >= 90) return "A+";
        else if (marks >= 80) return "A";
        else if (marks >= 70) return "B";
        else if (marks >= 60) return "C";
        else if (marks >= 50) return "D";
        else return "F";
    }

    @Override
    public String toString() {
        return String.format("| %-5d | %-20s | %-7.2f | %-5s |", id, name, marks, getGrade());
    }
}
