import java.util.ArrayList;
import java.util.List;

/**
 * Created by rober on 7/18/2017.
 */
public class Group {
    public List<String> students;
    public int maxStudents;
    public int MAX_STUDENTS = 50;

    Group(){
        this.students = new ArrayList<>();
        this.maxStudents = this.MAX_STUDENTS;
    }

   public boolean add(String stud){
        if(this.students.size() >= this.MAX_STUDENTS){
            return false;
        }

        this.students.add(stud);
        return true;
   }
}
