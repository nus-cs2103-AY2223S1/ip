package dukeprogram.facilities;

import java.io.Serializable;
import java.util.ArrayList;

import dukeprogram.Task;
import dukeprogram.storage.SaveManager;
import exceptions.KeyNotFoundException;

/**
 * TaskList is a class that encapsulates the required functionalities
 * of each task list. It also organises all the collected task lists.
 */
public class TaskList implements Serializable {
    private final ArrayList<Task> taskArrayList = new ArrayList<>(100);
    private String name;

    private TaskList(String name) {
        this.name = name;
    }

    /**
     * Initialises a new task list from the saved objects
     */
    public static TaskList loadTaskList() {
        TaskList current;

        try {
            current = SaveManager.load("tasklist");
        } catch (KeyNotFoundException e) {
            current = new TaskList("my tasks");
            SaveManager.save("tasklist", current);
        }

        return current;
    }


    public String getName() {
        return name;
    }


    public Task[] getAllTasks() {
        return taskArrayList.toArray(Task[]::new);
    }


    /**
     * Retrieves the size of all the stored task lists
     * @return the size of all task lists
     */
    public int getSize() {
        return taskArrayList.size();
    }

    /**
     * Adds a task to the current task list
     * @param task the task to add
     * @return whether the addition to the task list was successful
     */
    public boolean add(Task task) {
        boolean isValid = taskArrayList.add(task);
        SaveManager.save("tasklist", this);
        return isValid;
    }

    public void clear() {
        taskArrayList.clear();
        SaveManager.save("tasklist", this);
    }

    public Task get(int index) {
        return taskArrayList.get(index);
    }

    /**
     * Removes a task from the task list
     * @param index the index of the task to remove
     * @return the task that was removed if the index was valid, otherwise null
     */
    public Task remove(int index) {
        assert index < taskArrayList.size() && index >= 0;
        Task removedTask = taskArrayList.remove(index);
        SaveManager.save("tasklist", this);
        return removedTask;
    }
}
