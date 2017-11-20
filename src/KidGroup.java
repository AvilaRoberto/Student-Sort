import java.util.ArrayList;
import java.util.List;

/**
 * This class describes a KidGroup with a name, and a list of students.
 */
public class KidGroup {
    private String groupName;
    private List<String> students;
    private int maxStudents;
    private int MAX_STUDENTS = 15;

    KidGroup(){
        this.groupName = "n/a";
        this.students = new ArrayList<>();
        this.maxStudents = this.MAX_STUDENTS;
    }

    KidGroup(String name){
        this.groupName = name;
        this.students = new ArrayList<>();
        this.maxStudents = this.MAX_STUDENTS;
    }

    public List<String> getStudentList(){
        return this.students;
    }

   public boolean add(String stud){
        if(this.students.size() >= this.MAX_STUDENTS){
            return false;
        }

        this.students.add(stud);
        return true;
   }

   public ArrayList<String> getStudents(){
       return new ArrayList<>(this.students);
   }

   public void printGroup(){
       System.out.println("**" + this.groupName + " - " + this.students.size() + "**");
       for(String s: this.students){
           System.out.println(s);
       }
       System.out.println();
   }

   public int getSize(){
       return this.students.size();
   }

   public int getMax(){
       return this.maxStudents;
   }

   public String getGroupName() { return this.groupName; }
}
