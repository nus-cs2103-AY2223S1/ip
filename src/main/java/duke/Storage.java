package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Storage class that contains methods for storing and loading tasks.
 */
public class Storage {

    private String filePath;

    /**
     * Constructs a Storage object.
     *
     * @param filePath The assigned file path to the txt file that stores tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes an array of tasks to a file
     *
     * @param taskList The taskList to write from.
     */
    public void writeToFile(TaskList taskList) {
        File data = new File(this.filePath);
        try {
            data.createNewFile();
            FileWriter writer = new FileWriter(this.filePath);
            writer.write(taskList.getWrite());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads from the given storage file (.txt).
     *
     * @return The TaskList read from the storage file
     * @throws DukeException
     */
    public TaskList readFromFile() throws DukeException {
        TaskList taskList = new TaskList();
        int numTasks = 0;
        try {
            File obj = new File(this.filePath);
            Scanner reader = new Scanner(obj);
            while (reader.hasNextLine()) {
                ArrayList<String> split = new ArrayList<String>(
                        Arrays.asList(reader.nextLine().split(" - ")));
                String type = split.remove(0);
                boolean done = split.remove(0).equals("true");
                String rest = String.join(" ", split);
                switch (type) {
                case "D":
                    taskList.addTask(rest, "deadline", done, true);
                    break;
                case "E":
                    taskList.addTask(rest, "event", done, true);
                    break;
                case "T":
                    taskList.addTask(rest, "todo", done, true);
                    break;
                default:
                    throw new DukeException("Unrecognised task :(");
                }
                numTasks++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            return new TaskList();
        }
        return taskList;
    }
}
