package storage;

import data.TaskList;
import exceptions.DukeException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {
    private final String filePath;

    /**
     * Constructs a new Storage object that stores/reads from file path.
     * @param filePath File path to be read from/written to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves a task list to the file in the file path.
     * @param tasks Task list to be saved.
     */
    public void save(TaskList tasks) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a task list from the file in the file path.
     * @return Task list that was read from the file path.
     * @throws DukeException If loading from the stored file path failed (e.g. file does not exist).
     */
    public TaskList load() throws DukeException {
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Object o = ois.readObject();
            if (o instanceof TaskList) {
                return (TaskList) o;
            }
        } catch (Exception e) {
            throw new DukeException("Loading file failed");
        }
        return null;
    }
}
