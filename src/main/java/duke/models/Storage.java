package duke.models;

import duke.exceptions.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * The storage class provides methods to load and save tasks to disk.
 *
 * @author Zhu Yuanxi
 */
public class Storage {
    private static final String taskDirectoryName = "src/main/data/";
    private static final String taskFileName = "tasklist.txt";
    private static final String taskFilePath = taskDirectoryName + taskFileName;

    /**
     * Parses the saved string as a task object.
     *
     * @param text The string containing the task object representation to be parsed.
     * @return The task object represented by the saved string.
     * @throws DukeException If the saved string does not match with the required patterns.
     */
    private static Task parseTextToTask(String text) throws DukeException{
        String[] taskDetails = text.split("\\s\\|\\s", 0);
        for (String t : taskDetails) {
            System.out.println(t);
        }
        switch (taskDetails[0]) {
            case("T"):
                // TODO: Replace with Enums
                return new Todo(taskDetails[2], taskDetails[1].equals("1"));
            case("D"):
                // TODO: Replace with Enums
                return new Deadline(taskDetails[2], LocalDate.parse(taskDetails[3]), taskDetails[1].equals("1"));
            case("E"):
                // TODO: Replace with Enums
                return new Event(taskDetails[2],LocalDate.parse(taskDetails[3]), taskDetails[1].equals("1"));
        }
        throw new DukeException("Cannot parse saved tasks!");
    }

    /**
     * Loads the tasks from disk.
     *
     * @return The Tasklist containing all the saved tasks.
     * @throws DukeException If the path to file does not exist and the file cannot be created.
     */
    public static TaskList loadTasksFromDisk() throws DukeException {
        try {
            // Create folder and file if not exist
            File dir = new File(taskDirectoryName);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File myFile = new File(taskFilePath);
            myFile.createNewFile();

            Scanner fileReader = new Scanner(myFile);

            TaskList taskList = new TaskList();
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                taskList.AddTask(parseTextToTask(data));
            }
            fileReader.close();
            return taskList;
        } catch (IOException e) {
            throw new DukeException("Cannot create file to save tasks!");
        }
    }

    /**
     * Saves tasks to disk.
     *
     * @param tasks The TaskList to be converted to strings and saved to disk.
     * @throws DukeException If and I/O error occurs.
     */
    public static void saveTaskToDisk(TaskList tasks) throws DukeException{
        try {
            FileWriter out = new FileWriter(taskFilePath);
            tasks.forEach((task) -> {
                try {
                    out.write(task.formatForSave() + "\n");
                } catch (IOException e) {
                    // TODO: Add error handling
                    System.out.println("An error occurred.");
                }
            });
            out.close();
        } catch (IOException e) {
            // TODO: Add error handling
            System.out.println("An error occurred.");
        }
    }
}
