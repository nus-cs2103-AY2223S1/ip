package duke;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a tasklist to track existing tasks to be completed.
 */
public class TaskList {
    private List<Task> oldTasks;

    public TaskList(List<Task> oldTasks) {
        this.oldTasks = oldTasks;
    }

    /**
     * Adds a task to the tasklist.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        oldTasks.add(task);
    }

    /**
     * Removes a task from the tasklist.
     *
     * @param index Task number to remove.
     * @return String representation of task removed.
     */
    public Task removeTask(int index) {
        Task task;
        task = oldTasks.remove(index-1);
        return task;
    }

    /**
     * Returns Total number of tasks stored.
     *
     * @return Total number of tasks.
     */
    public int size() {
        return oldTasks.size();
    }


    /**
     * Filters the tasklist based on str.
     *
     * @param str Match criteria.
     * @return List of tasks that matches str.
     */
    public List<String> findMatches(String str) {
        List<String> lst = new ArrayList<>();
        for (int i = 0; i < oldTasks.size(); i++) {
            String task = oldTasks.get(i).toString();
            if (task.contains(str)) {
                lst.add(task);
            }
        }
        return lst;
    }


    /**
     * Returns task in oldTasks based on task number.
     *
     * @param num num Index of Task in oldTasks.
     * @return Task in old Tasklist
     */
    public Task getOldTask(int num) {
        return oldTasks.get(num);
    }

    /**
     * Replaces the Task of oldtask.
     *
     * @param index Position of task in list.
     * @param Task Task.
     */
    public void setOldTasks(int index, Task task) {
        oldTasks.set(index,task);
        return;
    }

    /**
     * Returns list of old tasks.
     *
     * @return List of old tasks.
     */
    public List<Task> getOldTasks() {
        return oldTasks;
    }

    /**
     * Sorts the tasklist.
     */
    public void sort() {
        Collections.sort(oldTasks);
    }


}
