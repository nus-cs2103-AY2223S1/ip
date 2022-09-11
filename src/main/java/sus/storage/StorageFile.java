package sus.storage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sus.DukeException;
import sus.task.Deadline;
import sus.task.Event;
import sus.task.Task;
import sus.task.TaskList;
import sus.task.Todo;

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
        try {
            BufferedWriter writer = Files.newBufferedWriter(path);
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
     * Returns an empty arraylist of Task if the file does not exist.
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();

        try {
            List<String> contents = Files.readAllLines(path);

            for (String line : contents) {
                String[] inputArray = Arrays.stream(line.split("\\|")).map(String::trim).toArray(String[]::new);
                String taskType = inputArray[0];
                String description = inputArray[2];
                LocalDate date = LocalDate.parse(inputArray[3]);
                Task task = null;

                switch (taskType) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    task = new Deadline(description, date);
                    break;
                case "E":
                    task = new Event(description, date);
                    break;
                default:
                    break;
                }

                assert task != null;
                task.setDone(inputArray[1].equals("1"));
                tasks.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
