import java.util.ArrayList;
import java.util.List;

/**
 * This class describes a student by their, first and last name, and their
 * list of prioritized courses.
 *
 * Created by rober on 6/30/2017.
 */
public class Student {
    private String first;
    private String last;
    public List<String> priorities;

    /**
     * Class constructor.
     */
    Student(){
        this.first = "n/a";
        this.last = "n/a";
        this.priorities = new ArrayList<>();
    }

    /**
     * Class constructor specifying a student by first and last name.
     */
    Student(String f, String l){
        this.first = f;
        this.last = l;
        this.priorities = new ArrayList<>();
    }

    /**
     * Class constructor specifying a student by first and last name
     * and prio list.
     */
    Student(String f, String l, List<String> prios){
        this.first = f;
        this.last = l;
        this.priorities = new ArrayList<>(prios);
    }

    /**
     * Returns the students last and first name, separated by a comma as a string.
     *
     * @return  a name as a string.
     */
    public String getFullName(){
        return this.last + "," + this.first;
    }

    /**
     * Returns the number of items in the students prio list as an integer.
     *
     * @return an integer describing the size of the students prio list.
     */
    public int getNumberPriorities(){
        return this.priorities.size();
    }

    /**
     * Prints the priority list, each item separated by a newline.
     */
    public void printPrios(){
        for(String p: this.priorities){
            System.out.println(p);
        }
    }

    /**
     * @return students priority List.
     */
    public List<String> getPriorities(){
        return this.priorities;
    }
}
