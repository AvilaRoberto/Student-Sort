import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException{
        double start = System.currentTimeMillis();

//        CourseStudentReader ss = new CourseStudentReader();
        Sort ss = new Sort();

        /*
        System.out.println("**Number of courses: " + ss.getNumberOfCourses());
        ss.printAllCourses(); System.out.println();

        System.out.println("**Number of students: " + ss.getNumberOfStudents());
        ss.printAllStudents(); System.out.println();
        */

        ss.sort();
        ss.printCourseAndStudents();

        System.out.println("TIME: " + (System.currentTimeMillis() - start) / 1000.0);
    }
}
