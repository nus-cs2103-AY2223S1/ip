import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the current tasks in the chatbot, which loads data from the hard disk (if data exists) whenever Duke
 * starts up
 */
public class Storage {
    // Used in the encoding of task data
    public static final String DELIMITER = "|";
    private static final String STORAGE_FILE_PATH = "data/duke.txt";

    private final List<Task> taskList = new ArrayList<>(100);

    /**
     * Constructs a storage for the chatbot, which attempts to read from a local file and update the current task
     * list to the tasks specified in the local file
     */
    Storage() {
        try {
            // Create storage file (and subdirectories) if they do not exist
            File storageFile = new File(STORAGE_FILE_PATH);
            createFileAndSubdirectories(storageFile);

            // Read the existing data in the storage file into the current task list
            Scanner scanner = new Scanner(storageFile);
            while (scanner.hasNextLine()) {
                String taskData = scanner.nextLine();
                this.addTask(decodeTask(taskData));
            }
            scanner.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (DukeException exception) {
            System.out.println(exception.toString());
        }
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
            decodedTask = new Deadline(taskValues[2], taskValues[1].equals("1"), taskValues[3]);
            break;
        }
        case "E": {
            if (taskValues.length != 4) {
                throw new DukeException("     ☹ OOPS!!! Invalid format for event.\n" + taskData);
            }
            decodedTask = new Event(taskValues[2], taskValues[1].equals("1"), taskValues[3]);
            break;
        }
        }
        return decodedTask;
    }

    /**
     * Overwrites the data inside the storage file with the current task list
     */
    public void saveTasks() {
        try {
            File storageFile = new File(STORAGE_FILE_PATH);
            createFileAndSubdirectories(storageFile);

            FileWriter fileWriter = new FileWriter(storageFile, false);
            for (Task task : this.taskList) {
                fileWriter.write(task.encode(DELIMITER) + "\n");
            }
            fileWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (DukeException exception) {
            System.out.println(exception.toString());
        }
    }

    /**
     * Retrieves the size of the current task list
     *
     * @return The size of the current task list
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Retrieves the task at a given index.
     *
     * @param index The specified index.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Removes the task at a given index.
     *
     * @param index The specified index.
     * @return The removed task.
     */
    public Task removeTask(int index) {
        return this.taskList.remove(index);
    }

    /**
     * Adds task to the current task list
     *
     * @param task The specified task.
     * @return Boolean of whether the task was added successfully.
     */
    public boolean addTask(Task task) {
        return this.taskList.add(task);
    }
}
