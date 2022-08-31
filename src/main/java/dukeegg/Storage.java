package dukeegg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.DukeException;
import exceptions.InvalidTaskException;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskType;
import task.Todo;


/**
 * Deals with loading tasks from the file and saving tasks in the file whenever chatbot starts / shuts down.
 */
public class Storage {
    // Used in the encoding of task data.
    public static final String DELIMITER = "|";
    private final File file;

    /**
     * Constructs a storage to store / retrieve tasks from some file path.
     *
     * @param filePath The specified path for the storage file.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Creates file and subdirectories leading to the file if they have not been created.
     *
     * @param file The specified path to the file.
     * @throws DukeException when a subdirectory or the file is unable to be created.
     */
    private static void createFileAndSubdirectoriesIfFileNotFound(File file) throws DukeException {
        File parent = file.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            throw new DukeException("Unable to create storage file");
        }
    }

    /**
     * Decodes task from storage file and returns the corresponding task.
     *
     * @param taskData The specified task data.
     * @return The corresponding task based on the taskData.
     * @throws DukeException when the values of the tasks are not valid.
     */
    public static Task decodeTask(String taskData) throws DukeException {
        // Backslashes in split method is necessary as | is a metacharacter in regex.
        String[] taskValues = taskData.split("\\|", 4);
        Task decodedTask;
        switch (taskValues[0]) {
        case "T": {
            if (taskValues.length != 3) {
                throw new InvalidTaskException(TaskType.T, taskData);
            }
            decodedTask = new Todo(taskValues[2], taskValues[1].equals("1"));
            break;
        }
        case "D": {
            if (taskValues.length != 4) {
                throw new InvalidTaskException(TaskType.D, taskData);
            }
            decodedTask = new Deadline(taskValues[2], taskValues[1].equals("1"), LocalDateTime.parse(taskValues[3],
                    Task.DATE_TIME_PARSER));
            break;
        }
        case "E": {
            if (taskValues.length != 4) {
                throw new InvalidTaskException(TaskType.E, taskData);
            }
            decodedTask = new Event(taskValues[2], taskValues[1].equals("1"), LocalDateTime.parse(taskValues[3],
                    Task.DATE_TIME_PARSER));
            break;
        }
        default:
            throw new InvalidTaskException(taskData);
        }
        return decodedTask;
    }

    /**
     * Loads data from the storage file into current task list.
     *
     * @return The list of tasks retrieved from the storage file.
     * @throws DukeException if an I/O error occurred or the task format is unrecognised.
     */
    public List<Task> load() throws DukeException {
        List<Task> loadedTasks = new ArrayList<>();
        try {
            createFileAndSubdirectoriesIfFileNotFound(this.file);

            // Read the existing data in the storage file into the current task list.
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String taskData = scanner.nextLine();
                loadedTasks.add(decodeTask(taskData));
            }
            scanner.close();
        } catch (IOException exception) {
            throw new DukeException("Error opening files");
        }
        return loadedTasks;
    }

    /**
     * Overwrites the data inside the storage file with the current task list.
     */
    public void saveTasks(TaskList taskList) {
        try {
            createFileAndSubdirectoriesIfFileNotFound(this.file);

            FileWriter fileWriter = new FileWriter(this.file, false);
            for (int i = 0; i < taskList.size(); i++) {
                fileWriter.write(taskList.getTask(i).encode(DELIMITER) + "\n");
            }
            fileWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (DukeException exception) {
            System.out.println(exception.toString());
        }
    }
}
