package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.exception.TaskIndexOutOfBoundsException;
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
     * Creates a TaskList object from a List of tasks.
     *
     * @param tasks List of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates a TaskList object containing an empty List of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a Task object to the List of tasks.
     *
     * @param task Task object to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the Task object at index taskIndex from the List of tasks.
     *
     * @param taskIndex Index of the task in the List of tasks.
     * @return The deleted Task object.
     * @throws TaskIndexOutOfBoundsException If the taskIndex is not within range of the List of tasks.
     */
    public Task delete(int taskIndex) throws TaskIndexOutOfBoundsException {
        if (!isValidIndex(taskIndex)) {
            throw new TaskIndexOutOfBoundsException(taskIndex, this.size());
        }
        Task task = this.tasks.get(taskIndex - 1);
        tasks.remove(taskIndex);
        return task;
    }

    /**
     * Marks the Task object at index taskIndex in the List of tasks as completed.
     *
     * @param taskIndex Index of the task in the List of tasks.
     * @return The Task object marked as completed.
     * @throws TaskIndexOutOfBoundsException If the taskIndex is not within range of the List of tasks.
     */
    public Task mark(int taskIndex) throws TaskIndexOutOfBoundsException {
        if (!isValidIndex(taskIndex)) {
            throw new TaskIndexOutOfBoundsException(taskIndex, this.size());
        }
        Task task = tasks.get(taskIndex - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Unmarks the Task object at index taskIndex in the List of tasks as completed.
     * In other words, marks the Task object as incomplete.
     *
     * @param taskIndex Index of the task in the List of tasks.
     * @return The Task object unmarked as completed.
     * @throws TaskIndexOutOfBoundsException If the taskIndex is not within range of the List of tasks.
     */
    public Task unmark(int taskIndex) throws TaskIndexOutOfBoundsException {
        if (!isValidIndex(taskIndex)) {
            throw new TaskIndexOutOfBoundsException(taskIndex, this.size());
        }
        Task task = tasks.get(taskIndex - 1);
        task.unmarkAsDone();
        return task;
    }

    /**
     * Returns the number of tasks in the List of tasks.
     * @return Size of the List of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Filters the list of Task objects in the TaskList, keep Task objects with descriptions matching the search term.
     * Returns the list of found Task objects as a new TaskList.
     *
     * @param searchTerm Search term used to find the list of matching Tasks.
     * @return TaskList containing the list of found Task objects.
     */
    public TaskList find(String searchTerm) {
        List<Task> foundTasksList = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(searchTerm)) {
                foundTasksList.add(task);
            }
        }
        return new TaskList(foundTasksList);
    }

    /**
     * Returns the List of tasks.
     *
     * @return List of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Prints all the tasks in the List of tasks.
     */
    public void listTasks() {
        int len = tasks.size();
        if (len == 0) {
            System.out.println("       YOU HAVE NO TASKS");
        } else {
            for (int i = 0; i < len; i++) {
                Task task = tasks.get(i);
                System.out.printf("   %d.%s%n", i + 1, task);
            }
        }
    }

    /**
     * Checks if index is a valid index of a Task object in the List of tasks.
     * @param index Index of a Task object.
     * @return True, if index is a valid index.
     */
    private boolean isValidIndex(int index) {
        return index >= 1 && index <= this.size();
    }
}
