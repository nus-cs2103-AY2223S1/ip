package dukeprogram.facilities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dukeprogram.storage.SaveManager;
import dukeprogram.tasks.Task;
import exceptions.KeyNotFoundException;

/**
 * TaskList is a class that encapsulates the required functionalities
 * of each task list. It also organises all the collected task lists.
 */
public class TaskList implements Serializable {
    private final ArrayList<Task> taskArrayList;
    /**
     * Creates a new task list
     */
    public TaskList() {
        taskArrayList = new ArrayList<>();
    }

    /**
     * Initialises a new task list from the saved objects
     */
    public static TaskList loadTaskList() {
        TaskList current;

        try {
            current = SaveManager.load("tasklist");
        } catch (KeyNotFoundException e) {
            current = new TaskList();
            SaveManager.save("tasklist", current);
        }

        return current;
    }

    @JsonIgnore
    public Task[] getAllTasks() {
        return taskArrayList.toArray(Task[]::new);
    }

    /**
     * Searches for all tasks with a substring in their names and returns
     * an array of tasks that contain the substring
     * @param substring the substring to be found within task names
     * @return an array of tasks that match the substring
     */
    public Task[] findTasks(String substring) {
        Pattern pattern = Pattern.compile(String.format("(.*)%s(.*)", substring));

        return taskArrayList.stream()
                .filter(task -> pattern.matcher(task.getName()).matches())
                .toArray(Task[]::new);
    }


    /**
     * Retrieves the size of all the stored task lists
     * @return the size of all task lists
     */
    @JsonIgnore
    public int getSize() {
        return taskArrayList.size();
    }

    /**
     * Adds a task to the current task list
     * @param task the task to add
     * @return whether the addition to the task list was successful
     */
    public boolean add(Task task) {
        if (taskArrayList.contains(task)) {
            return false;
        }

        taskArrayList.add(task);
        return true;
    }

    /**
     * Clears the entire task list completely
     */
    public void clear() {
        taskArrayList.clear();
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
        return taskArrayList.remove(index);
    }

    public int indexOf(Task task) {
        return taskArrayList.indexOf(task);
    }
}
