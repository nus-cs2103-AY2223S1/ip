package duke.models;

import duke.exceptions.DukeException;

import java.io.File;
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
    private static final String TASK_DIRECTORY_NAME = "src/main/data/";
    private static final String TASK_FILE_NAME = "tasklist.txt";
    private static final String TASK_FILE_PATH = TASK_DIRECTORY_NAME + TASK_FILE_NAME;

    /**
     * Parses the saved string as a task object.
     *
     * @param text The string containing the task object representation to be parsed.
     * @return The task object represented by the saved string.
     * @throws DukeException If the saved string does not match with the required patterns.
     */
    private static Task parseTextToTask(String text) throws DukeException {
        String[] taskDetails = text.split("\\s\\|\\s", 0);
        String label = taskDetails[0];
        String status = taskDetails[1];
        String taskDescription = taskDetails[2];

        switch (label) {
        case("T"):
            return new Todo(taskDescription, status.equals("1"));
        case("D"):
            return new Deadline(taskDescription,
                    LocalDate.parse(taskDetails[3]),
                    status.equals("1"));
        case("E"):
            return new Event(taskDescription,
                    LocalDate.parse(taskDetails[3]),
                    status.equals("1"));
        case("RE"):
            System.out.println("heree");
            LocalDate at = LocalDate.parse(taskDetails[3]);
            LocalDate startDate = LocalDate.parse(taskDetails[4]);
            LocalDate endDate = LocalDate.parse(taskDetails[5]);
            int interval = Integer.parseInt(taskDetails[6]);
            boolean isDone = status.equals("1");
            return new RecurringEvent(taskDescription, at, startDate, endDate, interval, isDone);
        default:
            throw new DukeException("Cannot parse saved tasks!");
        }
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
            File dir = new File(TASK_DIRECTORY_NAME);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File myFile = new File(TASK_FILE_PATH);
            myFile.createNewFile();

            Scanner fileReader = new Scanner(myFile);

            TaskList taskList = new TaskList();
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                taskList.addTask(parseTextToTask(data));
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
     * @return the number of tasks saved to disk.
     * @throws DukeException If and I/O error occurs.
     */
    public static int saveTaskToDisk(TaskList tasks) throws DukeException{
        try {
            FileWriter out = new FileWriter(TASK_FILE_PATH);
            tasks.forEach((task) -> {
                try {
                    out.write(task.formatForSave() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            out.close();
            return tasks.size();
        } catch (IOException e) {
            throw new DukeException("Failed to save tasks to disk!");
        }
    }
}
