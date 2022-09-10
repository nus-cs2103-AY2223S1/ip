package duke.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * A storage in Duke that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private static final String HOME = System.getProperty("user.home");
    private static final String FILE_NAME = "duke.txt";
    private static final String DIR_NAME = "data";
    private static final Path FILE_PATH = Paths.get(HOME, DIR_NAME, FILE_NAME);
    private static final Path DIR_PATH = Paths.get(HOME, DIR_NAME);

    /**
     * Creates a new instance of storage.
     */
    public Storage() {
        initialize();
    }

    /**
     * Initializes the storage, creates the directory and file if they are not present.
     *
     * @throws DukeException If the directory or file is missing but unable to create.
     */
    private void initialize() throws DukeException {
        try {
            if (!isDirectoryPresent()) {
                Files.createDirectory(DIR_PATH);
            }
        } catch (IOException e) {
            throw new DukeException("Unable to create new data folder!");
        }

        try {
            if (!isFilePresent()) {
                Files.createFile(FILE_PATH);
            }
        } catch (IOException e) {
            throw new DukeException("Unable to create new data file!");
        }
    }


    private boolean isDirectoryPresent() {
        return Files.exists(DIR_PATH);
    }

    private boolean isFilePresent() {
        return Files.exists(FILE_PATH);
    }

    /**
     * Loads the tasks from the file.
     *
     * @return The array list of tasks from the file.
     * @throws DukeException If the file is missing or unable to read from the file.
     */
    public ArrayList<Task> loadTasks() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            List<String> tasks = Files.readAllLines(FILE_PATH);
            for (String task : tasks) {
                Task decodedTask = decodeTaskFromString(task);
                taskList.add(decodedTask);
            }
        } catch (IOException e) {
            throw new DukeException("Unable to read from file!");
        }

        return taskList;
    }

    /**
     * Adds a task to the file.
     *
     * @param task The task to be added to the file.
     * @throws DukeException If the file is missing or unable to write to the file.
     */
    public void appendTaskToFile(Task task) throws DukeException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH.toString(), true);
            fw.write(encodeTaskToString(task));
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unable write to file!");
        }
    }

    /**
     * Overwrites the tasks in the file with the given tasks.
     *
     * @param taskList The given list of tasks to be written to the file.
     * @throws DukeException If the file is missing or unable to write to the file.
     */
    public void writeAllTasksToFile(TaskList taskList) throws DukeException {
        try {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.getTask(i);
                list.add(encodeTaskToString(task));
            }
            Files.write(FILE_PATH, list);
        } catch (IOException e) {
            throw new DukeException("Unable write to file!");
        }
    }

    /**
     * Decode the tasks from the file and creates the corresponding task.
     *
     * @param encodedTask The task to be decoded.
     * @return The corresponding task object from the encoded task.
     */
    private Task decodeTaskFromString(String encodedTask) {
        String[] components = encodedTask.split(" \\| ", 3);
        Task task;
        switch (components[0]) {
        case "T":
            task = new Todo(components[2]);
            break;
        case "D":
            String[] deadlineInputs = components[2].split(" \\| ", 2);
            task = new Deadline(deadlineInputs[0], LocalDateTime.parse(deadlineInputs[1]));
            break;
        case "E":
            String[] eventInputs = components[2].split(" \\| ", 2);
            task = new Event(eventInputs[0], LocalDateTime.parse(eventInputs[1]));
            break;
        default:
            task = null;
        }

        // "1" indicates duke.task is done
        if (components[1].equals("1")) {
            task.markAsDone();
        }

        return task;
    }

    private String encodeTaskToString(Task task) {
        return task.encode();
    }
}
