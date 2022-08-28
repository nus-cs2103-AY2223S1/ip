package skylark.task;

import java.util.ArrayList;

import skylark.skylark.SkylarkException;
import skylark.utils.Storage;

/** Represents a list of Tasks that is stored in the system. */
public class TaskList {
    /** Data structure that is used to store the Tasks */
    private final ArrayList<Task> taskArrayList;

    /** Represents a Storage object used for file operations */
    private final Storage storage;

    /**
     * Represents a TaskList object. <br><br>
     * Initialises a new Storage object and call its readFromFile function to store
     * tasks into the TaskList.
     *
     * @param filePath File path to the text file that contains the stored tasks.
     */
    public TaskList(String filePath) {
        this.storage = new Storage(filePath);
        this.taskArrayList = this.storage.readFromFile();
    }

    /**
     * Returns the size of the TaskList.
     * The number represents the number of tasks currently stored in the system.
     *
     * @return Size of the TaskList.
     */
    public int size() {
        return this.taskArrayList.size();
    }

    /**
     * Returns the Task object stored at the particular index of the TaskList.
     *
     * @param index Index of the Task object.
     * @return Task object that is stored at the index.
     */
    public Task get(int index) {
        return this.taskArrayList.get(index);
    }

    /**
     * Adds the Task object into the TaskList.
     *
     * @param task Task object that is to be added.
     */
    public void add(Task task) {
        this.taskArrayList.add(task);
    }

    /**
     * Remove the Task object from the TaskList.
     *
     * @param index Index of the Task object in the TaskList.
     */
    public void remove(int index) {
        this.taskArrayList.remove(index);
    }

    /**
     * Returns whether the particular index exists in the TaskList.
     *
     * @param index Index of the Task object in the TaskList.
     * @return Boolean whether the index exists in the TaskList.
     */
    public boolean doesIndexExist(int index) {
        return index >= 0 && index < this.taskArrayList.size();
    }

    /**
     * Save the current TaskList into the storage file.
     * Throws a SkylarkException in an event of a file operation exception.
     *
     * @throws SkylarkException If file operation fails.
     */
    public void saveToFile() throws SkylarkException {
        this.storage.saveToFile(this.taskArrayList);
    }
}
