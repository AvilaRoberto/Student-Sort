import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class reads student and course information from a file, and creates
 * the appropriate Student and Course objects.
 */
public class CourseStudentReader {
    //List of string course names.
    protected List<String> courseNames;
    //List of Course objects.
    protected List<Course> courses;

    //List of Student objects.
    protected List<Student> students;

    //File that contains student anc course names.
    protected String fileToRead;

    /**
     * Default constructor.
     */
    CourseStudentReader() throws IOException{
        this.courseNames = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
        this.fileToRead = "src/files/registrations.csv";
        readData();
    }

    /**
     * Constructor that specifies a list of courses and students, and a file to read.
     * @param cours List of courses.
     * @param studs List of students.
     * @param file  String name of file to read.
     */
    CourseStudentReader(List<Course> cours, List<Student> studs, String file){
        this.courseNames = new ArrayList<>();
        this.courses = new ArrayList<>(cours);
        this.students = new ArrayList<>(studs);
        this.fileToRead = file;

        for(Course c: this.courses){
            this.courseNames.add(c.getName());
        }
    }

    /**
     * Reads a .csv file to get student names, course names, and priority lists.
     *
     */
    private void readData() throws IOException{
        //Read STUDENTS from file.
        Reader inStudents = new FileReader(this.fileToRead);
        Iterable<CSVRecord> recordsStudents = CSVFormat.EXCEL.parse(inStudents);

        //Read file entries by row.
        int row = 0;
        for(CSVRecord rec: recordsStudents){
            if(row == 0){
                row += 1;
                continue;
            }

            //Make a new Student objects using full names and priorities.
            List<String> prios = new ArrayList<>();

            //Columns 2-5 are priorities.
            for(int i = 2; i < 6; ++i){
                if(rec.get(i).length() != 0) {
                    String cls = rec.get(i).toLowerCase();

                    //Ignore 'bus'...
                    if(cls.equals("bus")){
                        continue;
                    }
                    //Add course to prio list.
                    prios.add(cls);

                    //If first encounter of course, add name to list and
                    //make new object.
                    if(!courseNames.contains(cls)){
                        courseNames.add(cls);
//                        courses.add(new Course(cls));
                    }
                }
            }

            //Finally create Student object with first and last name, and list of priorities.
            //First and last names are in indexes 2, and 1.
            students.add(new Student(rec.get(1), rec.get(0), prios));
            row += 1;
        }

        //Sort names and create Course objects from the sorted list.
//        Collections.sort(this.courseNames);
        for(String s: this.courseNames){
            this.courses.add(new Course(s));
        }

        Collections.shuffle(this.students);

        makePeerLists(2);
    }

    /**
     * For each student x, find other students who share numOfsims prios and
     * add them to the peer list of x.
     */
    private void makePeerLists(int numOfSims) {
        for(Student std_1: this.students){      //student 1
            for(Student std_2: this.students){  //student 2

                //Skip self.
                if(std_1.getFullName().equals(std_2.getFullName())){
                    continue;
                }
                //check if students share 'numOfSims' priorities.
                if(std_1.sharePrioWith(std_2, numOfSims)){
                    std_1.addPeer(std_2.getFullName());
                }
            }
        }

    }

    /**
     * Prints course names separated by newlines.
     */
    public void printAllCourses(){
        for(String s: this.courseNames){
            System.out.println(s);
        }
    }

    /**
     * Prints student names separated by newlines.
     */
    public void printAllStudents(){
        for(Student s: this.students){
            System.out.println(s.getFullName());
        }
    }

    /**
     * Prints each course followed by the appropriate student list.
     */
    public void printCourseAndStudents(){
        for(Course crs: this.courses){
            crs.printStudents();
        }
    }

    /**
     * Print student names followed by their peer list.
     */
    public void printStudentsAndPeers(){
        for(Student s: this.students){
            System.out.println("**" + s.getFullName() + "**");
            s.printPeers();
            System.out.println();
        }
    }

    /**
     * Print student names and the number of peers that they have.
     */
    public void printNumberPeers(){
        for(Student std: this.students){
            System.out.println(std.getFullName() + "\t\t" + std.getNumberPeers());
        }
    }

    /**
     * Print student names followed by their list of prios.
     */
    public void printStudentsAndPrios(){
        for(Student std: this.students){
            System.out.println("**" + std.getFullName() + "**");
            std.printPrios();
            System.out.println();
        }
    }

    /**
     * Counts the number of courses in the course list.
     * @return number of courses as int.
     */
    public int getNumberOfCourses(){
        return this.courses.size();
    }

    /**
     * Counts the number of students in the student list.
     * @return number of students as int.
     */
    public int getNumberOfStudents(){
        return this.students.size();
    }

}
