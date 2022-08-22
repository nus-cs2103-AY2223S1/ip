package duke.task;

import duke.exception.TaskIndexOutOfBoundsException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of tasks added by the user.
 *
 * @author njxue
 * @version v0.1
 */
public class TaskList {
    /** List of tasks */
    private List<Task> tasks;

    /**
     * Creates a <code>TaskList</code> object from a <code>List</code> of tasks.
     * 
     * @param tasks List of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates a <code>TaskList</code> object containing an empty <code>List</code> of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a <code>Task</code> object to the <code>List</code> of tasks.
     * 
     * @param task <code>Task</code> object to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes the <code>Task</code> object at index <code>taskIndex</code> from the <code>List</code> of tasks.
     * 
     * @param taskIndex Index of the task in the <code>List</code> of tasks.
     * @return The deleted <code>Task</code> object.
     * @throws TaskIndexOutOfBoundsException If the <code>taskIndex</code> is not within range of the <code>List</code> of tasks.
     */
    public Task delete(int taskIndex) throws TaskIndexOutOfBoundsException {
        if (!isValidIndex(taskIndex)) {
            throw new TaskIndexOutOfBoundsException(taskIndex, this.size());
        }
        Task task = this.tasks.get(taskIndex - 1);
        this.tasks.remove(taskIndex);
        return task;
    }

    /**
     * Marks the <code>Task</code> object at index <code>taskIndex</code> in the <code>List</code> of tasks as completed.
     *
     * @param taskIndex Index of the task in the <code>List</code> of tasks.
     * @return The <code>Task</code> object marked as completed.
     * @throws TaskIndexOutOfBoundsException If the <code>taskIndex</code> is not within range of the <code>List</code> of tasks.
     */
    public Task mark(int taskIndex) throws TaskIndexOutOfBoundsException {
        if (!isValidIndex(taskIndex)) {
            throw new TaskIndexOutOfBoundsException(taskIndex, this.size());
        }
        Task task = this.tasks.get(taskIndex - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Unmarks the <code>Task</code> object at index <code>taskIndex</code> in the <code>List</code> of tasks as completed.
     * In other words, marks the <code>Task</code> object as incomplete.
     *
     * @param taskIndex Index of the task in the <code>List</code> of tasks.
     * @return The <code>Task</code> object unmarked as completed.
     * @throws TaskIndexOutOfBoundsException If the <code>taskIndex</code> is not within range of the <code>List</code> of tasks.
     */
    public Task unmark(int taskIndex) throws TaskIndexOutOfBoundsException {
        if (!isValidIndex(taskIndex)) {
            throw new TaskIndexOutOfBoundsException(taskIndex, this.size());
        }
        Task task = this.tasks.get(taskIndex - 1);
        task.unmarkAsDone();
        return task;
    }

    /**
     * Returns the number of tasks in the <code>List</code> of tasks.
     * @return Size of the <code>List</code> of tasks.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the <code>List</code> of tasks.
     * @return <code>List</code> of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Prints all the tasks in the <code>List</code> of tasks.
     */
    public void listTasks() {
        int len = this.tasks.size();
        if (len == 0) {
            System.out.println("       YOU HAVE NO TASKS");
        } else {
            for (int i = 0; i < len; i++) {
                Task task = this.tasks.get(i);
                System.out.printf("   %d.%s%n", i + 1, task);
            }
        }
    }

    /**
     * Checks if <code>index</code> is a valid index of a <code>Task</code> object in the <code>List</code> of tasks.
     * @param index Index of a <code>Task</code> object.
     * @return True, if <code>index</code> is a valid index.
     */
    public boolean isValidIndex(int index) {
        return index >= 1 && index <= this.size();
    }
}
