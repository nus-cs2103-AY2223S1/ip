package duke;

import exceptions.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file whenever Duke starts / shuts down
 */
public class Storage {
    // Used in the encoding of task data
    public static final String DELIMITER = "|";
    private final String filePath;

    /**
     * Constructs a storage to store / retrieve tasks from some file path
     *
     * @param filePath The specified path for the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates file and subdirectories leading to the file if they have not been created
     *
     * @param file The specified path to the file.
     * @throws DukeException when a subdirectory or the file is unable to be created.
     */
    private static void createFileAndSubdirectories(File file) throws DukeException {
        File parent = file.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            throw new DukeException("Unable to create storage file");
        }
    }

    /**
     * Decodes task from storage file and returns the corresponding task
     *
     * @param taskData The specified task data.
     * @return The corresponding task based on the taskData.
     * @throws DukeException when the values of the tasks are not valid.
     */
    public static Task decodeTask(String taskData) throws DukeException {
        // Backslashes in split method is necessary as | is a metacharacter in regex
        String[] taskValues = taskData.split("\\|", 4);
        Task decodedTask = null;
        switch (taskValues[0]) {
        case "T": {
            if (taskValues.length != 3) {
                throw new DukeException("     ☹ OOPS!!! Invalid format for todo.\n" + taskData);
            }
            decodedTask = new Todo(taskValues[2], taskValues[1].equals("1"));
            break;
        }
        case "D": {
            if (taskValues.length != 4) {
                throw new DukeException("     ☹ OOPS!!! Invalid format for deadline.\n" + taskData);
            }
            decodedTask = new Deadline(taskValues[2], taskValues[1].equals("1"), LocalDateTime.parse(taskValues[3],
                    Task.dateTimeParser));
            break;
        }
        case "E": {
            if (taskValues.length != 4) {
                throw new DukeException("     ☹ OOPS!!! Invalid format for event.\n" + taskData);
            }
            decodedTask = new Event(taskValues[2], taskValues[1].equals("1"), LocalDateTime.parse(taskValues[3],
                    Task.dateTimeParser));
            break;
        }
        }
        return decodedTask;
    }

    public List<Task> load() throws DukeException {
        List<Task> loadedTasks = new ArrayList<>();
        try {
            // Create storage file (and subdirectories) if they do not exist
            File storageFile = new File(this.filePath);
            createFileAndSubdirectories(storageFile);

            // Read the existing data in the storage file into the current task list
            Scanner scanner = new Scanner(storageFile);
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
     * Overwrites the data inside the storage file with the current task list
     */
    public void saveTasks(TaskList taskList) {
        try {
            File storageFile = new File(this.filePath);
            createFileAndSubdirectories(storageFile);

            FileWriter fileWriter = new FileWriter(storageFile, false);
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
