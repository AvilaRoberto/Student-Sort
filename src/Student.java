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
    private List<String> priorities;
    private List<String> peers;

    /**
     * Class constructor.
     */
    Student(){
        this.first = "n/a";
        this.last = "n/a";
        this.priorities = new ArrayList<>();
        this.peers = new ArrayList<>();
    }

    /**
     * Class constructor specifying a student by first and last name.
     */
    Student(String f, String l){
        this.first = f;
        this.last = l;
        this.priorities = new ArrayList<>();
        this.peers = new ArrayList<>();
    }

    /**
     * Class constructor specifying a student by first and last name
     * and prio list.
     */
    Student(String f, String l, List<String> prios){
        this.first = f;
        this.last = l;
        this.priorities = new ArrayList<>(prios);
        this.peers = new ArrayList<>();
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
            System.out.println("\t" + p);
        }
    }

    /**
     * @return students priority List.
     */
    public List<String> getPriorities(){
        return new ArrayList<String>(this.priorities);
    }

    public List<String> getPeers() { return this.peers; }

    public void addPeer(String p){
        this.peers.add(p);
    }

    /**
     * Determines if the two objects have at least one prio in common.
     *
     * @param std the Student we are comparing with.
     * @param sims number of wanted similarities.
     * @return true if the students share 'sims' prio.
     *          false otherwise.
     */
    public boolean sharePrioWith(Student std, int sims){

        int priosShared = 0;
        for(String s: this.priorities){
            if(priosShared == sims){
                return true;
            }
            if(std.priorities.contains(s)){
                priosShared += 1;
            }
        }
        return priosShared == sims;

    }

    public void printPeers(){
        for(String s: this.peers){
            System.out.println("\t" + s);
        }
    }

    public int getNumberPeers(){
        return this.peers.size();
    }

    public String getPeer(){
        if(this.peers.isEmpty()){
            return null;
        }
        return this.peers.get(0);
    }

}
