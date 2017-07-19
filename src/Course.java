import java.util.ArrayList;
import java.util.List;

/**
 * This class describes a course. It contains a name, and a list of students.
 */

public class Course {
    private String name;
    private List<String> students;
    private int numberOfStudents;
    private int maxStudents;
    private int MAX_STUDENTS = 15;

    Course(){
        this.name = "n/a";
        this.students = new ArrayList<>();
        this.numberOfStudents = this.students.size();
        this.maxStudents = MAX_STUDENTS;
    }

    /**
     * Constructor that specifies a course by name.
     *
     * @param n the name of the course as a string.
     */
    Course(String n){
        this.name = n;
        this.maxStudents = MAX_STUDENTS;
        this.students = new ArrayList<>(this.maxStudents);
        this.numberOfStudents = this.students.size();
    }

    /**
     * Constructor that specifies a course by name, a list of students,
     * and a max course size.
     *
     * @param n the course name as a string.
     * @param s the list of students as an ArrayList.
     * @param m the max class size as an int.
     */
    Course(String n, ArrayList<String> s, int m){
        this.name = n;
        this.students = new ArrayList<>(s.size());
        this.students.addAll(s);
        this.maxStudents = m;
        this.numberOfStudents = this.students.size();
    }

    /**
     * @return the course name as a string.
     */
    public String getName(){
        return this.name;
    }
}
