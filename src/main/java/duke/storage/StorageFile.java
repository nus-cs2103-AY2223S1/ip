package duke.storage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents the file used to store the task list.
 */
public class StorageFile {

    /** Default file path used if the user does not provide the file path */
    public static final String DEFAULT_STORAGE_FILEPATH = "/data/duke.txt";

    private final Path path;

    /**
     * Creates a directory and file based on user specified file path.
     *
     * @param filePath file path to store the data for the task list
     */
    public StorageFile(String filePath) {
        path = Paths.get(filePath + DEFAULT_STORAGE_FILEPATH);
        try {
            Files.createDirectory(path.getParent());
            Files.createFile(path);
        } catch (IOException ignored) {
            // FileAlreadyExitsException (under IOException) ignored.
        }
    }

    /**
     * Saves the {@code TaskList} data to the storage file.
     *
     * @param taskList task list data to save
     */
    public void save(TaskList taskList) throws DukeException {
        BufferedWriter writer;
        try {
            writer = Files.newBufferedWriter(path);
            writer.write("");
            writer.flush();
            writer.write(taskList.encodeToString());
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Error writing to file: " + path);
        }
    }

    /**
     * Loads the {@code TaskList} data from the storage file and return it.
     * Returns an empty arraylist of task if the file does not exist.
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();

        if (!Files.exists(path)) {
            return tasks;
        }

        try {
            List<String> contents = Files.readAllLines(path);

            String[] inputArray;
            String taskType;
            String description;

            for (String line : contents) {
                inputArray = Arrays.stream(line.split("\\|")).map(String::trim).toArray(String[]::new);
                taskType = inputArray[0];
                description = inputArray[2];
                Task task = null;

                switch (taskType) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    task = new Deadline(description, inputArray[3]);
                    break;
                case "E":
                    task = new Event(description, inputArray[3]);
                    break;
                default:
                    break;
                }

                if (task != null) {
                    if (inputArray[1].equals("1")) {
                        task.mark();
                    } else {
                        task.unmark();
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
