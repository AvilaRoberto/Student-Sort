import java.util.ArrayList;
import java.util.List;

/**
 * Created by rober on 6/30/2017.
 */
public class Student {
    private String first;
    private String last;
    private List<String> priorities;

    Student(){
        this.first = "n/a";
        this.last = "n/a";
        this.priorities = new ArrayList<>();
    }

    Student(String f, String l){
        this.first = f;
        this.last = l;
        this.priorities = new ArrayList<>();
    }

    Student(String f, String l, List<String> prios){
        this.first = f;
        this.last = l;
        this.priorities = new ArrayList<>(prios);
    }

    public String getFullName(){
        return this.last + "," + this.first;
    }

    public int getNumberPriorities(){
        return this.priorities.size();
    }

    public void printPrios(){
        for(String p: this.priorities){
            System.out.println(p);
        }
    }
}
