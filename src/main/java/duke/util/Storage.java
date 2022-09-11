package duke.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Class to manage file storage operations.
 */
public class Storage {
    private static final String ERROR_MESSAGE_NO_STORAGE_FILE = "No storage file found";
    private static final String ERROR_MESSAGE_READ_STORAGE_FILE_ERROR = "Error reading from storage file";

    public final String dataPath;
    public final String storageName;

    /**
     * Constructor for {@code Storage}.
     *
     * @param dataPath Path to data storage directory.
     * @param storageName Name of storage file.
     */
    public Storage(String dataPath, String storageName) {
        this.dataPath = dataPath;
        this.storageName = storageName;
    }

    /**
     * Stores all tasks in given {@code TaskList} to storage.
     *
     * @param taskList {@code TaskList} containing tasks to save to storage.
     * @throws DukeException Checked exceptions that may occur when writing to storage file.
     */
    public void writeTaskListToStorage(TaskList taskList) throws DukeException {
        try {
            File directory = new File(dataPath);
            if (!directory.exists() || !directory.isDirectory()) {
                directory.mkdir();
            }
            File file = new File(dataPath + "/" + storageName);
            FileWriter fw = new FileWriter(file);
            StringBuilder toWrite = new StringBuilder();
            for (String taskString : taskList.getAllTasksInStorageFormat()) {
                toWrite.append(taskString + System.lineSeparator());
            }
            fw.write(toWrite.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error writing to storage file");
        }
    }

    /**
     * Loads all tasks in storage into a {@code List}.
     *
     * @return {@code List} object containing {@code Task} objects.
     * @throws DukeException Checked exceptions that may occur when reading from storage file.
     */
    public List<Task> load() throws DukeException {
        try {
            List<Task> tasks = new ArrayList<>();
            File file = new File(dataPath + "/" + storageName);
            if (!file.exists()) {
                throw new DukeException(ERROR_MESSAGE_NO_STORAGE_FILE);
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String taskString = scanner.nextLine();
                tasks.add(StorageParser.parseTaskString(taskString));
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException(ERROR_MESSAGE_READ_STORAGE_FILE_ERROR);
        }
    }
}
