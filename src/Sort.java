import java.io.IOException;

/**
 * Created by rober on 7/18/2017.
 */
public class Sort extends CourseStudentReader {
    Sort() throws IOException{
    }

    void sort(){
        for(Student s: this.students){
            if(s.priorities.isEmpty()){
                continue;
            }
            for(String crs: s.priorities) {

                for (Course c : this.courses) {
                    if (crs.equals(c.getName())) {
                        c.addStudent(s);
                    }
                }
            }
        }
    }

}
