package Duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a tasklist to track existing tasks to be completed.
 */
public class TaskList {
    private List<Task> oldTasks;
    private List<Task> newTasks = new ArrayList<>();

    public TaskList(List<Task> oldTasks) {
        this.oldTasks = oldTasks;
    }

    /**
     * Adds a task to the tasklist.
     * @param Task Task to be added.
     */
    public void addTask(Task task) {
        newTasks.add(task);
    }

    /**
     * Removes a task from the tasklist.
     * @param index Task number to remove.
     * @return String representation of task removed.
     */
    public Task removeTask(int index) {
        Task task;
        if (index > oldTasks.size()) {
            task = newTasks.remove(index-oldTasks.size()-1);
        } else {
            task = oldTasks.remove(index-1);
        }
        return task;
    }

    /**
     * Returns Total number of tasks stored.
     * @return Total number of tasks.
     */
    public int size() {
        return oldTasks.size() + newTasks.size();
    }

    /**
     * Returns The number of tasks previously stored.
     * @return Size of oldTasks.
     */
    public int oldTasksSize() {
        return oldTasks.size();
    }

    /**
     * Filters the tasklist based on str.
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
        for(int i=0; i < newTasks.size(); i++) {
            String task = newTasks.get(i).toString();
            if (task.contains(str)) {
                lst.add(task);
            }
        }
        return lst;
    }

    /**
     * Returns task in newTasks based on task number.
     * @param num Index of Task in newTasks.
     * @return Task in new Tasklist
     */
    public Task getNewTasks(int num) {
        return newTasks.get(num);
    }

    /**
     * Returns task in oldTasks based on task number.
     * @param num num Index of Task in oldTasks.
     * @return Task in old Tasklist
     */
    public Task getOldTasks(int num) {
        return oldTasks.get(num);
    }

    /**
     * Replacing the Task of oldtask.
     * @param index Position of task in list.
     * @param Task Task.
     */
    public void setOldTasks(int index, Task task) {
        oldTasks.set(index,task);
        return;
    }

    /**
     * Replacing the Task of newtask.
     * @param index Position of task in list.
     * @param Task Task.
     */
    public void setNewTasks(int index, Task task) {
        newTasks.set(index,task);
        return;
    }

    /**
     * Returns list of new tasks.
     * @return List of new tasks.
     */
    public List<Task> getNewTasks() {
        return newTasks;
    }

    /**
     * Return list of old tasks.
     * @return List of old tasks.
     */
    public List<Task> getOldTasks() {
        return oldTasks;
    }


}
