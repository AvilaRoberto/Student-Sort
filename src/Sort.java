import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class extends CourseStudentReader and it takes the read in data and
 * sorts it using specific rules.
 */
public class Sort extends CourseStudentReader {
    private List<KidGroup> kidGroups;
    private int numberOfGroups;
    private int DEFAULT_NUMBER_GROUPS = 5;
    private Object[] FILE_HEADER;
    private List<String> FILE_HEAD;
    private String fileToWrite = "src/files/results.csv";

    Sort() throws IOException{
        this.kidGroups = new ArrayList<>();
        this.numberOfGroups = this.DEFAULT_NUMBER_GROUPS;
        for(int i = 0; i < this.numberOfGroups; ++i){
            String s = Integer.toString(i);
            this.kidGroups.add(new KidGroup(s));
        }
        makeHeader();

    }

    private void makeHeader(){
        this.FILE_HEADER = new Object[this.courseNames.size()+1];
        this.FILE_HEADER[0] = "STUDENT NAME";
        for(int i = 1; i < this.FILE_HEADER.length; ++i){
            this.FILE_HEADER[i] = this.courseNames.get(i-1).toUpperCase();
        }

        this.FILE_HEAD = new ArrayList<>();
        this.FILE_HEAD.add("LAST,");
        this.FILE_HEAD.add("FIRST,");
        this.FILE_HEAD.add("GROUP,");
        Collections.addAll(this.courseNames);
        for(String header: this.courseNames){
            this.FILE_HEAD.add(header.toUpperCase() + ",");
        }
        this.FILE_HEAD.add("NUM PRIOS,");
    }

    public List<KidGroup> getGroupList(){
        return this.kidGroups;
    }

    /**
     * Will write group information to a file.
     *
     */
    public void writeResults() throws IOException{

        FileWriter fileWriter = null;
        CSVPrinter csvFilePrinter = null;

        CSVFormat csvFileFormat =
                CSVFormat.EXCEL.withRecordSeparator("\n");

        try{
            fileWriter = new FileWriter("src/files/results.csv");
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

            //Write headers
            csvFilePrinter.printRecord(this.FILE_HEAD);

            boolean skip = true;

            //Go through each group.
            for(KidGroup kidGroup : this.kidGroups){

                //This will store all the information to be written to the file.
                List<String> studAndPrios = new ArrayList<String>();

                //This will store the current kidGroups student list.
                ArrayList<String> groupStuds = kidGroup.getStudents();

                //Find each students prios.
                for(String stud: groupStuds){

                    //Store students name.
                    studAndPrios.add(stud);

                    //This will store the prios.
                    List<String> studPrios = new ArrayList<>();

                    //Look through master Student list.
                    for(Student studMain: this.students){

                        //When the student is found, get prios.
                        if(stud.equals(studMain.getFullName())){
                            studPrios = studMain.getPriorities();
                        }
                    }

                    for(String courseName: this.courseNames){
                        if(studPrios.contains(courseName)){
                            studAndPrios.add("X");
                        }
                        else{
                            studAndPrios.add("_");
                        }
                    }
                }
                csvFilePrinter.printRecord(studAndPrios);

            }

        }catch(Exception e){
            System.out.println("Error in CsvFileWriter");
            e.printStackTrace();
        }finally{
            try{
                fileWriter.flush();
                fileWriter.close();
                csvFilePrinter.close();
            }catch( IOException e){
                System.out.println("Error while flushing/closing fileWriter/csvPrinter");
                e.printStackTrace();
            }
        }
    }

    private void writeRes() throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileToWrite));
        for(String header: this.FILE_HEAD){
            writer.write(header);
        }
        writer.write("\n");

        //Go through each group.
        for(KidGroup kidGroup : this.kidGroups){

            //This will store all the information to be written to the file.
            List<String> studAndPrios = new ArrayList<String>();

            //This will store the current kidGroups student list.
            ArrayList<String> groupStuds = kidGroup.getStudents();

            //Find each students prios.
            for(String stud: groupStuds){


                //Store students name.
                studAndPrios.add(stud);
                writer.write(stud + ",");
                writer.write(kidGroup.getGroupName() + ",");

                //This will store the prios.
                List<String> studPrios = new ArrayList<>();

                //Look through master Student list.
                for(Student studMain: this.students){

                    //When the student is found, get prios.
                    if(stud.equals(studMain.getFullName())){
                        studPrios = studMain.getPriorities();
                    }
                }

                int numPrios = 0;
                for(String courseName: this.courseNames){
                    if(studPrios.contains(courseName)){
                        studAndPrios.add("X");
                        writer.write("X" + ",");
                        numPrios += 1;
                    }
                    else{
                        studAndPrios.add("_");
                        writer.write("" + ",");
                    }
                }
                writer.write(numPrios + ",");
                writer.write("\n");
            }
            writer.write("\n");

        }


        writer.close();
    }

    void sort()throws IOException{
        ArrayList<String> trackingList = new ArrayList<>();
        for(Student std: this.students){
            trackingList.add(std.getFullName());
        }

        int currentGroup = 0;

        for(Student std: this.students){
            //Check if student is in list.
            if(!trackingList.contains(std.getFullName())){
                //If student is not on the list/already in a group,
                //continue to the next student.
                currentGroup = (currentGroup + 1) % this.numberOfGroups;
                continue;
            }

            //Check that the MAX number of students for a group has not been reached.
            //Iterate to the next one if so.
            while(this.kidGroups.get(currentGroup).getSize() >= this.kidGroups.get(currentGroup).getMax()){
                currentGroup = (currentGroup + 1) % this.numberOfGroups;
            }

            //Add student to current group.
            this.kidGroups.get(currentGroup).add(std.getFullName());
            //Remove student from the list, indicating the student is now in a group.
            trackingList.remove(std.getFullName());

            //Get students peer list.
            ArrayList<String> ps = new ArrayList<>(std.getPeers());
            Collections.shuffle(ps);

            for(String peer: ps){

                if(trackingList.contains(peer)){
                    //If peer is in the list, add to current group.
                    this.kidGroups.get(currentGroup).add(peer);
                    //Remove peer immediately after.
                    trackingList.remove(peer);
                    break;
                }
            }

            currentGroup = (currentGroup + 1) % this.numberOfGroups;

        }

        writeRes();
    }

    public void printGroups(){
        for(KidGroup g: this.kidGroups){
            g.printGroup();
        }
    }

}
