package duke.storage;

import duke.common.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the storage of the task list in the file system.
 *
 * @author Tan Jun Wei
 */
public class Storage {
    /** The file to store the task list */
    private final File dataFile;

    /**
     * Constructs a new Storage object with the given file path.
     *
     * @param filePath The file path to store the task list.
     */
    public Storage(String filePath) {
        this.dataFile = new File(filePath);
    }

    private void createDataFile() throws DukeException {
        if (!dataFile.exists()) {
            try {
                dataFile.getParentFile()
                        .mkdirs();
                dataFile.createNewFile();
            } catch (IOException e) {
                throw new DukeException("I/O error occurred");
            }
        }
    }

    /**
     * Adds one task to the task list file.
     * Faster than saveAllTask() because it does not re-write existing tasks.
     *
     * @param task The task to be saved.
     * @throws DukeException If file cannot be written to.
     */
    public void saveTask(Task task) throws DukeException {
        try {
            FileWriter fw = new FileWriter(dataFile, true); // create a FileWriter in append mode
            fw.write(task.encode() + "\n");
            fw.close();
        } catch (IOException e) {
            throw new DukeException("I'm sorry, but I can't write to the file.");
        }
    }

    /**
     * Adds all task to the task list file, overwriting existing file contents.
     * Slower than saveTask() because it may re-write existing tasks.
     *
     * @param taskList The task list to be saved.
     * @throws DukeException If file cannot be written to.
     */
    public void saveAllTasks(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(dataFile);
            fw.write(taskList.encode());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("\tOOPS!!! I'm sorry, but I can't write to the file.");
        }
    }

    /**
     * Loads the task list from the file.
     *
     * @return ArrayList of tasks loaded.
     * @throws DukeException If file cannot be found.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        createDataFile();
        Scanner scanner;
        try {
            scanner = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            throw new DukeException("Data file not found");
        }
        while (scanner.hasNext()) {
            String l = scanner.nextLine();
            tasks.add(Task.decode(l));
        }
        scanner.close();
        return tasks;
    }
}
