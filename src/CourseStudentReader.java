import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rober on 6/30/2017.
 */
public class CourseStudentReader {
    private List<String> courseNames;
    private List<Course> courses;
    private List<Student> students;
    private String fileToRead;

    CourseStudentReader() throws IOException{
        this.courseNames = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
        this.fileToRead = "src/files/SUN-summer-17.csv";
        readData();
    }

    CourseStudentReader(List<Course> cours, List<Student> studs, String file){
        this.courseNames = new ArrayList<>();
        this.courses = new ArrayList<>(cours);
        this.students = new ArrayList<>(studs);
        this.fileToRead = file;

        for(Course c: this.courses){
            this.courseNames.add(c.getName());
        }
    }

    private void readData() throws IOException{
        //Read STUDENTS from file.
        Reader inStudents = new FileReader(this.fileToRead);
        Iterable<CSVRecord> recordsStudents = CSVFormat.EXCEL.parse(inStudents);

        //Read file entries by row.
        int row = 0;
        for(CSVRecord rec: recordsStudents){

            //Make a new Student objects using full names and priorities.
            List<String> prios = new ArrayList<>();

            //Columns 5-9 are priorities.
            for(int i = 2; i < 5; ++i){
                if(rec.get(i).length() != 0) {
                    String cls = rec.get(i).toLowerCase();
                    //Ignore 'bus'.
                    if(cls.equals("bus")){
                        continue;
                    }
                    //Add course to prio list.
                    prios.add(cls);
                    //If first encounter of course, add name to list and
                    //make new object.
                    if(!courseNames.contains(cls)){
                        courseNames.add(cls);
                        courses.add(new Course(cls));
                    }
                }
            }

            //Finally create Student object with first and last name, and list of priorities.
            //First and last names are in indexes 2, and 1.
            students.add(new Student(rec.get(1), rec.get(0), prios));
        }
    }

    public void printAllCourses(){
        for(Course c: this.courses){
            System.out.println(c.getName());
        }
    }

    public void printAllStudents(){
        for(Student s: this.students){
            System.out.println(s.getFullName());
        }
    }

    public int getNumberOfCourses(){
        return this.courses.size();
    }

    public int getNumberOfStudents(){
        return this.students.size();
    }

}
