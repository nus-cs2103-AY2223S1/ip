package seedu.duke.storage;

import seedu.duke.exception.DukeException;
import seedu.duke.task.Task;
import seedu.duke.task.Event;
import seedu.duke.task.Deadline;
import seedu.duke.task.ToDo;
import seedu.duke.task.TaskList;


import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.Objects;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;

/**
 * The Storage class handles the creation of a file specified by the duke.txt file.
 */
public class Storage {
    /* The filepath that the file should be created or read from. */
    String filePath;

    /**
     * Creates a Storage object.
     *
     * @param filePath The filePath of whether the file should be created or read from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Returns an ArrayList<Task> after reading the contents of the file specified by the filePath variable.
     */
    public ArrayList<Task> load() throws DukeException {
        // Reused from https://www.w3schools.com/java/java_files_create.asp
        // Reused from https://www.w3schools.com/java/java_files_read.asp

        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File directory = new File("/data");
            if (!directory.exists()) {
                directory.mkdir();
            }

            File myObj = new File(this.filePath);
            if (myObj.createNewFile()) {
                return tasks;
            } else {
                return readFile(myObj, tasks);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Saves the data from the TaskList object into a file specified by the filePath.
     */
    public void writeToFile(TaskList tasks) {
        try {
            FileWriter myWriter = new FileWriter(this.filePath);
            ArrayList<Task> data = tasks.getAllTasks();
            for (int i = 0; i < data.size(); i++) {
                myWriter.write(data.get(i).toFileString() + System.getProperty("line.separator"));
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Returns an ArrayList<Task> object after reading the contents of the file.
     *
     * @param myObj A file object.
     * @param tasks An array with which the contents of the file read would be written to.
     * @return An ArrayList containing the tasks read from file.
     * @throws DukeException If there is an error found when reading the file.
     */
    private ArrayList<Task> readFile(File myObj, ArrayList<Task> tasks) throws DukeException {
        try {
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] output = data.split(" , ", 0);
                boolean isDone = Integer.parseInt(output[1]) == 1;
                if (Objects.equals(output[0], "E")) {
                    tasks.add(new Event(output[2], output[3], isDone));
                } else if (Objects.equals(output[0], "D")) {
                    tasks.add(new Deadline(output[2], output[3], isDone));
                } else if (Objects.equals(output[0], "T")) {
                    tasks.add(new ToDo(output[2], isDone));
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("An error occurred.");
        }
        return tasks;
    }
}
