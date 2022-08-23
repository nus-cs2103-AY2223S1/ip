package tako;

import java.util.ArrayList;
import java.util.List;

import tako.task.Task;

/**
 * Tracks and operates on a task list.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor for an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for task list with existing tasks.
     *
     * @param tasks List of tasks to initialize with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a task.
     *
     * @param taskNumber Position of the task in the task list.
     * @throws TakoException If the task number is < 0 or
     *                       greater than the number of tasks in the task list.
     */
    public void mark(int taskNumber) throws TakoException {
        if (taskNumber < 0 || taskNumber > tasks.size() - 1) {
            throw new TakoException("The task number to mark does not exist.");
        }
        Task task = tasks.get(taskNumber);
        task.markAsDone();
    }

    /**
     * Removes a task.
     *
     * @param taskNumber Position of the task in the task list.
     * @throws TakoException If the task number is < 0 or
     *                       greater than the number of tasks in the task list.
     */
    public Task remove(int taskNumber) throws TakoException {
        if (taskNumber < 0 || taskNumber > tasks.size() - 1) {
            throw new TakoException("The task number to delete does not exist.");
        }
        return tasks.remove(taskNumber);
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public int getSize() {
        return tasks.size();
    }
}
