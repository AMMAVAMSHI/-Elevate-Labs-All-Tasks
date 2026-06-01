import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentManager {
    private ArrayList<Student> students;
    private int nextId;

    public StudentManager() {
        this.students = new ArrayList<>();
        this.nextId = 1;
        loadSampleData();
    }

    private void loadSampleData() {
        students.add(new Student(nextId++, "Alice Johnson", 92.5));
        students.add(new Student(nextId++, "Bob Smith", 78.0));
        students.add(new Student(nextId++, "Carol White", 85.5));
    }

    // CREATE
    public Student addStudent(String name, double marks) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100.");
        }
        Student student = new Student(nextId++, name.trim(), marks);
        students.add(student);
        return student;
    }

    // READ - all
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    // READ - by ID
    public Optional<Student> findById(int id) {
        return students.stream().filter(s -> s.getId() == id).findFirst();
    }

    // UPDATE
    public boolean updateStudent(int id, String newName, double newMarks) {
        Optional<Student> opt = findById(id);
        if (opt.isPresent()) {
            Student s = opt.get();
            if (newName != null && !newName.trim().isEmpty()) {
                s.setName(newName.trim());
            }
            if (newMarks >= 0 && newMarks <= 100) {
                s.setMarks(newMarks);
            }
            return true;
        }
        return false;
    }

    // DELETE
    public boolean deleteStudent(int id) {
        return students.removeIf(s -> s.getId() == id);
    }

    public int getTotalStudents() {
        return students.size();
    }

    public double getClassAverage() {
        return students.stream().mapToDouble(Student::getMarks).average().orElse(0.0);
    }

    public Student getTopStudent() {
        return students.stream().max((a, b) -> Double.compare(a.getMarks(), b.getMarks())).orElse(null);
    }
}
