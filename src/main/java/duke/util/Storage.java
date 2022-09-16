package duke.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Handles reading/writing of tasks to a local txt file.
 *
 * @author bensohh
 * @version CS2103T AY 22/23 Sem 1 (G01)
 */
public class Storage {
    private String filePath;

    /**
     * Constructor to create an instance of Storage.
     *
     * @param filePath String representation of the relative file path
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Searches for the specified file and directory to generate a list of Tasks
     * from a local txt file.
     *
     * @return ArrayList of Task containing all the task specified in the txt file
     *     or an empty ArrayList of Task if the txt file is empty/does not exist
     * @throws IOException if an error occur when reading/writing to directories and file
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> taskStored = new ArrayList<>();
        File fileToLoad = new File(filePath);
        if (!fileToLoad.exists()) {
            File fileDirectory = new File("./data");
            if (!fileDirectory.exists()) {
                fileDirectory.mkdir();
                assert fileDirectory.exists(); // Assuming that the directory is created
                fileToLoad.createNewFile();
                assert fileToLoad.exists(); // Assuming that the file is created
                System.out.println("Creating new directory data and new file tasks.txt");
            } else {
                fileToLoad.createNewFile();
                assert fileToLoad.exists(); // Assuming that the file is created
                System.out.println("Creating new file tasks.txt");
            }
        }

        Scanner sc = new Scanner(fileToLoad);
        while (sc.hasNextLine()) {
            taskStored.add(formatString(sc.nextLine()));
        }
        return taskStored;
    }

    /**
     * Parses the data stored in the txt file and format them before creating new
     * tasks according to the task type, task status and task description.
     *
     * @param stringStored String representation of each line stored in the txt file
     * @return Task corresponding to the stored String after formatting
     */
    public Task formatString(String stringStored) {
        // To isolate the string containing the task description
        String s1 = stringStored.substring(7);
        // To isolate the string containing the task status
        String s2 = stringStored.substring(0, 6);
        // To keep track if the task has been marked completed
        boolean isCompleted = s2.contains("[X]");

        if (s2.contains("[T]")) {
            return new Todo(s1, isCompleted);
        } else if (s2.contains("[D]")) {
            String[] separateBy = s1.split(" \\(by: ", 2);
            return new Deadline(separateBy[0], separateBy[1].substring(0, separateBy[1].length() - 1), isCompleted);
        } else {
            assert s2.contains("[E]");
            String[] separateAt = s1.split(" \\(at: ", 2);
            return new Event(separateAt[0], separateAt[1].substring(0, separateAt[1].length() - 1), isCompleted);
        }
    }

    /**
     * Writes the list of tasks to a local txt file for storing.
     *
     * @param tasks List of tasks to be stored
     * @throws IOException if an error occurs while writing to the txt file
     */
    public void writeToFile(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        String dataToStore = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (i == 0) {
                dataToStore = tasks.getTask(i).toString();
            } else {
                dataToStore = dataToStore + "\n" + tasks.getTask(i).toString();
            }
        }
        fileWriter.write(dataToStore);
        fileWriter.close();
    }
}
