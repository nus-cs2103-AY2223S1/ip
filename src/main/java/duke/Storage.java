package duke;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Storage {

    private final ArrayList<Task> myList;

    Storage() {
        this.myList = new ArrayList<>();
    }

    /**
     * Set up master list with existing text file
     * Else, creates an empty list
     * @return ArrayList<Task>
     */
    public ArrayList<Task> setUp() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("duke.txt"));
            String line = br.readLine();
            while (line != null) {
                parser(line);
                line = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e){
            System.out.println("Existing text file not found, creating new file! :)");
        } catch (IOException e) {
            System.out.println("IO Exception Error.");
            e.printStackTrace();
        }
        return myList;
    }

    /**
     * Parses text file into Task Objects.
     * @param line in text file
     * Adds Task objects into master list 
     */
    public void parser(String line) {
        String taskType = Character.toString(line.charAt(1));
        switch (taskType){
            case "T":
                myList.add(new Todo(line.substring(7)));
                break;
            case "D":
                myList.add(new Deadline(line.substring(7).split(" \\(by")[0], 
                    dateFormatter(line.split("\\(by: ")[1].split("\\)")[0]))
                );
                break;
            case "E":
                myList.add(new Event(line.substring(7).split(" \\(at")[0], 
                    dateFormatter(line.split("\\(at: ")[1].split("\\)")[0]))
                );
                break;
            default:
                break;
        }
    }

    /**
     * Parses String Date into Java LocalDate Format
     * @return an instance of LocalDate
     */
    public static LocalDate dateFormatter(String myDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate curDate = LocalDate.parse(myDate, formatter);
        return curDate;
    }
}
