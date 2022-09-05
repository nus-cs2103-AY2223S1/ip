package duke;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private String path;

    Storage(String path) {
        this.path = path;
    }

    /**
     * Saves the given task list to the specified location. Tries to create a new file if one does not exist.
     *
     * @param tasks Task list to be saved.
     * @throws DukeException If the task list could not be saved.
     */
    public void saveTasks(List<Task> tasks) throws DukeException {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();
            fos.close();
        } catch (IOException e) {
            throw new DukeException("An I/O error occurred while writing to '" + path + "'");
        }
    }

    /**
     * Attempts to load a task list from the specified location.
     *
     * @return Loaded task list.
     * @throws DukeException If the task list could not be loaded.
     */
    public List<Task> loadTasks() throws DukeException {
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<Task> tasks = (List<Task>) ois.readObject();
            return tasks;
        } catch (IOException e) {
            throw new DukeException("An I/O error occurred while reading from '" + path + "'");
        } catch (ClassNotFoundException e) {
            throw new DukeException("Failed to read '" + path + "'");
        }
    }
}
