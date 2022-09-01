package duke;

import java.util.ArrayList;

/**
 * Contains methods and attributes relevant to storing and operating
 * on tasks added by user.
 * 
 * @author Siau Wee
 */
public class TaskList {

    private ArrayList<Task> addedTasks = new ArrayList<>(100);
    
    /**
     * Returns the size of the task array.
     * 
     * @return Number of tasks in the array
     */
    public int getSize() {
        return this.addedTasks.size();
    }

    /**
     * Returns the task at the specified array.
     */
    public Task getTask(int index) throws TaskNotFoundException {
        if (index > this.getSize() || index < 0) {
            throw new TaskNotFoundException(String.valueOf(index));
        }
        return this.addedTasks.get(index);
    }

    /**
     * Adds a given task to the task array.
     * 
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.addedTasks.add(task);
        Storage.saveToDirectory(this.addedTasks);
    }

    /**
     * Removes the task at the specified index from the array.
     * 
     * @param index The index at which the contained task is to be deleted.
     * @throws TaskNotFoundException If there is no task at the specified index.
     */
    public Task deleteTask(int index) throws TaskNotFoundException {
        if (index > this.getSize() || index < 0) {
            throw new TaskNotFoundException(String.valueOf(index));
        }
        Task removedTask = this.addedTasks.remove(index - 1);
        Storage.saveToDirectory(this.addedTasks);
        return removedTask;
    }

    /**
     * Searches for tasks in the task array with a specific sequence of chars
     * in their task name, then prints these tasks to output.
     * @param chars The sequence of chars to search tasks by
     */
    public ArrayList<String> findTask(String chars) {
        ArrayList<String> foundTasks = new ArrayList<>(100);
        for (int i = 0; i < this.getSize(); ++i) {
            Task searchedTask = this.addedTasks.get(i);
            if (searchedTask.doesNameContain(chars)) {
                foundTasks.add((i + 1) + ". " + searchedTask);
            }
        }
        return foundTasks;
    }

    /**
     * Marks the task at the specified index as done.
     * 
     * @param index The index at which the contained task is to be marked done.
     * @throws TaskNotFoundException If there is no task at the specified index.
     */
    public Task markTask(int index) throws TaskNotFoundException {
        if (index > this.getSize() || index < 0) {
            throw new TaskNotFoundException(String.valueOf(index));
        }
        Task taskToMark = this.addedTasks.get(index - 1);
        taskToMark.mark();
        Storage.saveToDirectory(this.addedTasks);
        return taskToMark;
    }

    /**
     * Marks the task at the specified index as undone.
     * 
     * @param index The index at which the contained task is to be marked undone.
     * @throws TaskNotFoundException If there is no task at the specified index.
     */
    public Task unmarkTask(int index) throws TaskNotFoundException {
        if (index > this.getSize() || index < 0) {
            throw new TaskNotFoundException(String.valueOf(index));
        }
        Task taskToUnmark = this.addedTasks.get(index - 1);
        taskToUnmark.unmark();
        Storage.saveToDirectory(this.addedTasks);
        return taskToUnmark;
    }

}
