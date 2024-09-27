package tasklist;

import java.util.ArrayList;
import java.util.List;

import tasks.Task;


/**
 * Utility class that handles CRUD operations regarding the task list in memory.
 */
public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task get(int i) {
        return this.taskList.get(i);
    }

    public int size() {
        return this.taskList.size();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void markTask(int index) {
        this.taskList.get(index).setIsComplete(true);
    }

    public void unmarkTask(int index) {
        this.taskList.get(index).setIsComplete(false);
    }

    /**
     * Deletes a task at a specified (zero-indexed) index in the task
     * list, and returns the deleted task.
     * Case where list is empty should be guarded against.
     *
     * @param index Index of task to be deleted, zero-indexed
     * @return The deleted task
     */
    public Task deleteTask(int index) {
        Task deletedTask = this.taskList.get(index);
        this.taskList.remove(index);
        return deletedTask;
    }

    /**
     * Returns true if the task list is empty.
     *
     * @return Boolean value representing if the task list is empty.
     */
    public boolean isEmpty() {
        return this.taskList.size() > 0;
    }
}
