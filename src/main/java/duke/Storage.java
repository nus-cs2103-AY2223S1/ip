package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Encapsulates a class extracted from the main logic to read and write data in to the storage.
 */
public class Storage {

    /**
     * Reads the text file and returns the stored list of tasks
     * @param input The location of the text file
     * @return The stored List of Tasks
     */
    public List<Task> readFile(String input) {
        List<Task> returnList = new ArrayList<>();
        try {
            File myObj = new File(input);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                returnList.add(stringToTask(data));
            }
            myReader.close();
            return returnList;
        } catch (FileNotFoundException e) {
            createFile(input);
            return new ArrayList<>();
        } catch (DukeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Takes the stored string and returns a single task that it represents
     * @param data The parsed task
     * @return The Task stored
     */
    private Task stringToTask(String data) throws DukeException {
        assert data.length() > 0;
        char type = (data.charAt(0));
        boolean done = (data.charAt(1)) == ('Y');
        Task task = null;
        if(type == 'T') {
            String description = data.substring(2);
            task = new Todo(description);
        }
        else if (type == 'D') {
            int indexOfDate = data.indexOf("/");
            String description = data.substring(2,indexOfDate);
            String date = data.substring(indexOfDate +1);
            task = new Deadline(description,getDate(date));
        }
        else if (type == 'E') {
            int indexOfDate = data.indexOf("/");
            String description = data.substring(2,indexOfDate);
            String date = data.substring(indexOfDate +1);
            task = new Event(description,getDate(date));
        }
        if(done && task != null){
            task.setDone();
        }
        return task;
    }

    /**
     * Creates a textfile to store the data if one does not exist.
     * @param input
     */
    private static void createFile(String input) {
        try {
            File myObj = new File(input);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Writes the tasks in the list to the specified location.
     * @param input
     */
    public void writeFile(List<Task> input, String location){
        assert input != null;
        assert location != null;
        try {
            FileWriter myWriter = new FileWriter(location);
            for(Task cur: input) {
                myWriter.write(cur.parseTask() + '\n');
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private static LocalDate getDate(String input) throws DukeException {
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e){
            throw new DukeException("☹ OOPS!!! Please format your date as yyyy-mm-dd format (e.g., 2019-10-15)");
        }
    }
}
