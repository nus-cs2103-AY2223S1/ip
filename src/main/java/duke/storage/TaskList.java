package duke.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import duke.task.Task;

/**
 * Represents a list that contains different tasks.
 */
public class TaskList {

    private List<Task> tasks;
    private int count = 0;

    /**
     * Constructs a new TaskList instance with no parameter.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList instance with a given List.
     *
     * @param tasks List of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        count = tasks.size();
    }

    /**
     * Goes through all tasks stored in the list.
     *
     * @return A string of a task list
     */
    public String iterate() {
        return Stream.iterate(1, x -> x + 1).limit(tasks.size())
                .sequential()
                .map(i -> i + ". " + tasks.get(i - 1).toString() + System.lineSeparator())
                .reduce("", (x, y) -> x + y);
    }

    /**
     * Returns the total number of tasks in the list as String.
     *
     * @return A string.
     */
    public String getCount() {
        return "Now you have " + this.count + " tasks in the list.";
    }

    /**
     * Finds the task in the list given an index.
     *
     * @param index Position of the task in the list.
     * @return The task found.
     */
    public Task findTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
        count++;
    }

    /**
     * Deletes a task from the list.
     *
     * @param task Task to be removed.
     */
    public void delete(Task task) {
        tasks.remove(task);
        count--;
    }

    /**
     * Returns a new TaskList that stores tasks
     * whose description contains the keyword.
     *
     * @param keyword Keyword to be checked.
     * @return A new TaskList.
     */
    public TaskList findMatchingTasks(String keyword) {
        TaskList newTaskList = new TaskList();
        for (Task task : tasks) {
            if (task.matchDescription(keyword)) {
                newTaskList.add(task);
            }
        }
        return newTaskList;
    }

    /**
     * Returns if the task list contains the given task.
     *
     * @param task Given task.
     * @return True if the task is already in the task list; false otherwise.
     */
    public boolean hasDuplicates(Task task) {
        return tasks.contains(task);
    }
}
