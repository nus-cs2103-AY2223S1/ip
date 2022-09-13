package duke.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import duke.data.exceptions.InvalidTaskException;
import duke.tasks.Task;

/**
 * Represents the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for a TaskList from a given list of tasks.
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor for a TaskList when there is an error loading the list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the list.
     * @return The number of tasks in the list.
     */
    public int numTasks() {
        return tasks.size();
    }

    /**
     * Returns a Task at the given index on the list.
     * @param index The index of the Task in the list.
     * @return The Task at the given index on the list.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks a Task as done or not done.
     * @param index The index of the Task to be marked as done or not done.
     * @param isDone True if the Task is to be marked as done and false if the Task is to
     *               be marked as not done.
     * @return The Task that has been marked as done or not done.
     * @throws InvalidTaskException If the task number is negative or greater than the number of tasks.
     */
    public Task changeTaskStatus(int index, boolean isDone) throws InvalidTaskException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskException();
        }

        Task task = tasks.get(index);
        task.changeStatus(isDone);
        return task;
    }

    /**
     * Adds a Task to the list of tasks.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a Task from the list of tasks.
     * @param index The index of the Task to be deleted.
     * @return The Task that has been deleted.
     * @throws InvalidTaskException If the task number is negative or greater than the number of tasks.
     */
    public Task deleteTask(int index) throws InvalidTaskException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskException();
        }

        Task task = tasks.get(index);
        tasks.remove(task);
        return task;
    }

    /**
     * Finds the tasks that occur on a specific date.
     * @param date The date to check if a Task occurs on.
     * @return A list of tasks that occur on the given date.
     */
    public List<Task> getTasksOnDate(String date) {
        return tasks
            .stream()
            .filter(task -> task.isOnDate(date))
            .collect(Collectors.toList());
    }

    /**
     * Finds matching tasks whose description contains any of the given keyword(s).
     * @param keywords The keyword(s) to check each Task's description for.
     * @return A list of tasks whose description contains any of the given keyword(s).
     */
    public List<Task> getTasksWithKeywords(String ... keywords) {
        return tasks
            .stream()
            .filter(task -> Arrays.stream(keywords).anyMatch(task::hasKeyword))
            .collect(Collectors.toList());
    }

    /**
     * Modifies the task list.
     * @param tasks The tasks for the new task list.
     */
    public void changeTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
