package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import duke.command.AddCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Provides the ability of to perform various actions to a text file stored in the project.
 */
public class Storage {
    public final String fileName;
    //public final String filePath;
    public final File myFile;
    private static final String home = System.getProperty("user.dir");
    private final java.nio.file.Path filePath;

    /**
     * Constructs a Storage instance provided the fileName and known filePath.
     *
     * @param fileName the name of the text document
     */
    Storage(String fileName) {
        this.fileName = fileName;
        this.filePath = java.nio.file.Paths.get(home, "data", this.fileName);
        this.myFile = initialise();
    }


    /**
     * Check if duke.txt file and its directory exists, if not create new file for storing task list.
     *
     * @return old duke.txt file or newly created duke.txt file
     */
    //@@author eesung00-reused
    //Reused from https://github.com/eesung00/ip/blob/master/src/main/java/duke/storage/FileManager.java
    // with minor modifications
    private File initialise() {
        File newDirectory = new File(this.filePath.getParent().toUri());
        File newFile = new File(this.filePath.toUri());
        try {
            if (!newDirectory.exists()) {
                newDirectory.mkdir();
                newFile.createNewFile();
                insertDummyTasks(newFile);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            assert false : "error in initialise";
        }
        return newFile;
    }

    /**
     * Initialise task list with a few dummy tasks.
     *
     * @param file that task list is stored.
     */
    private void insertDummyTasks(File file) {
        try {
            Task todo = new ToDo("dummyTodo ", AddCommand.validateDateString("2020-01-02"),
                    AddCommand.validateTimeString("0001"));
            Task deadline = new Deadline("dummyDeadline ", AddCommand.validateDateString("2050-12-31"),
                    AddCommand.validateTimeString("2359"));
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(todo.toString());
            myWriter.write(deadline.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Initialisation of dummy tasks failed!");
        }
    }

    /**
     * Adds text which converted from Task into the file.
     *
     * @param text a String consisting of the information of the Task.
     */
    public void write(String text) throws DukeException {
        try {
            FileWriter myWriter = new FileWriter(this.filePath.toFile());
            myWriter.write(text);
            myWriter.close();
        } catch (IOException e) {
            throw new DukeException("Uh uh! The system cannot find the path specified. "
                    + "Are you sure your file path is correct?");
        }
    }

    /**
     * Returns an ArrayList of Tasks read from the file.
     *
     * @return the ArrayList of Tasks which are added into the file previously.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.filePath.toFile()));
            String line;
            while ((line = br.readLine()) != null) {
                Task task = this.convertStringToTask(line);
                taskList.add(task);
            }
            br.close();
        } catch (IOException e) {
            throw new DukeException("Uh uh! The system cannot find the path specified. "
                    + "Are you sure your file path is correct?");
        }
        return taskList;
    }

    private Task convertStringToTask(String taskString) {
        String[] parts = taskString.split(",", 0);
        String taskType = parts[0];
        boolean isMarked = Integer.parseInt(parts[1]) == 1;
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
