package duke.component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents a storage that handles loading and saving tasks into the file.
 */
public class Storage {

    private final File file;

    /**
     * Constructs a new Storage.
     *
     * @param path Path to the file that stores the data.
     * @throws DukeException If an error occurs when making directories or creating file.
     */
    public Storage(Path path) throws DukeException {
        this.file = path.toFile();
        if (!this.file.exists()) {
            try {
                this.file.getParentFile().mkdirs();
                this.file.createNewFile();
            } catch (SecurityException e) {
                throw new DukeException("Unable to make directory!");
            } catch (IOException e) {
                throw new DukeException("Error occurred when creating file!");
            }
        }
    }

    /**
     * Loads the data from the file.
     *
     * @return TaskList of all tasks that is contained in the file.
     * @throws DukeException If there is no file located.
     */
    public TaskList load() throws DukeException {
        try {
            Scanner sc = new Scanner(this.file);
            TaskList tasks = new TaskList();
            while (sc.hasNextLine()) {
                Task task = Task.fromStorage(sc.nextLine());
                if (task != null) {
                    tasks.addTask(task);
                }
            }
            sc.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("Unable to locate file when trying to read from file!");
        }
    }

    /**
     * Saves the data into the file.
     *
     * @param tasks List of all tasks to be saved.
     * @throws DukeException If there is no file located or an error occurs during writing to file.
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(this.file);
            fileWriter.write(tasks.toStorage());
            fileWriter.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Unable to locate file when trying to write to file!");
        } catch (IOException e) {
            throw new DukeException("Error occurred when writing to file!");
        }
    }

}
