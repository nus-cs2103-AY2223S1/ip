package maria.task;

import java.io.IOException;
import java.util.ArrayList;

import maria.Storage;
import maria.util.StorageConverter;

public class TaskList extends ArrayList<Task> {

    private final Storage storage;

    public TaskList(Storage storage) {
        super();
        this.storage = storage;
    }

    private void syncStorageState() {
        try {
            this.storage.writeToFile(StorageConverter.tasksToString(this));
        } catch (IOException e) {
            System.out.println("Sync storage state failed. " + e.getMessage());
        }
    }

    /**
     * Adds a task as per the standard library List, and syncs with the storage file.
     * @param task The task to be added
     * @return Always true
     */
    @Override
    public boolean add(Task task) {
        boolean res = super.add(task);
        syncStorageState();
        return res;
    }

    /**
     * Removes a task as per the standard library List, and syncs with the storage file.
     * @param index The index of the task to be removed
     * @return The removed task
     */
    @Override
    public Task remove(int index) {
        Task t = super.remove(index);
        syncStorageState();
        return t;
    }

    /**
     * Removes a task as per the standard library List, and syncs with the storage file.
     * @param task The task object to be removed
     * @return The removed task
     */
    @Override
    public boolean remove(Object task) {
        boolean isRemoved = super.remove(task);
        syncStorageState();
        return isRemoved;
    }

    /**
     * Represents a method to be called to sync the storage file.
     *
     * This method should be called after the internal state of a Task has been modified.
     */
    public void mutatedTask() {
        syncStorageState();
    }

}
