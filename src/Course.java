import java.util.ArrayList;
import java.util.List;

/**
 * Created by rober on 6/30/2017.
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

    Course(String n){
        this.name = n;
        this.maxStudents = MAX_STUDENTS;
        this.students = new ArrayList<>(this.maxStudents);
        this.numberOfStudents = this.students.size();
    }

    Course(String n, ArrayList<String> s, int m){
        this.name = n;
        this.students = new ArrayList<>(s.size());
        this.students.addAll(s);
        this.maxStudents = m;
        this.numberOfStudents = this.students.size();
    }

    public String getName(){
        return this.name;
    }
}
