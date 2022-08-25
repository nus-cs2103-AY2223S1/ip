package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Provides the ability of to perform various actions to a text file stored in the project.
 */
public class Storage {
    public final String fileName;
    public final String filePath;
    public final File myFile;

    /**
     * Constructs a Storage instance provided the fileName and known filePath.
     *
     * @param fileName the name of the text document
     */
    Storage(String fileName) {
        this.fileName = fileName;
        this.filePath = "./data/" + fileName;
        this.myFile = new File(this.filePath);
    }

    /**
     * Adds text which converted from Task into the file.
     *
     * @param text a String consisting of the information of the Task.
     */
    public void write(String text) {
        try {
            FileWriter myWriter = new FileWriter(this.filePath);
            myWriter.write(text);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Uh uh! The system cannot find the path specified. "
                    + "Are you sure your file path is correct?");
        }
    }

    /**
     * Returns an ArrayList of Tasks read from the file.
     *
     * @return the ArrayList of Tasks which are added into the file previously.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.filePath));
            String line;
            while ((line = br.readLine()) != null) {
                Task task = this.convertStringToTask(line);
                taskList.add(task);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Uh uh! The system cannot find the path specified. "
                    + "Are you sure your file path is correct?");
        }
        return taskList;
    }

    private Task convertStringToTask(String taskString) {
        String[] parts = taskString.split(",", 0);
        String taskType = parts[0];
        boolean isMarked = (Integer.parseInt(parts[1]) == 1) ? true : false;
        String taskName = parts[2];
        LocalDate date = null;
        LocalTime time = null;
        if (parts.length >= 4) {
            date = LocalDate.parse(parts[3]);
            time = LocalTime.parse(parts[4]);
        }
        switch(taskType) {
        case "T":
            return new ToDo(taskName, isMarked, date, time);
        case "D":
            return new Deadline(taskName, isMarked, date, time);
        case "E":
            return new Event(taskName, isMarked, date, time);
        default:
            return new Task(taskName, isMarked, date, time);
        }
    }
}
