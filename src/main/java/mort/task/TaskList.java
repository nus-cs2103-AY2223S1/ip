package mort.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class that stores the list of tasks and methods to manipulate the tasks.
 */
public class TaskList {
    /** List of tasks */
    private ArrayList<Task> tasks;

    /**
     * Constructor to initialize list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor to initialize list of tasks using a given task list.
     * @param tasks The given task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints all tasks in the list.
     */
    public String list() {
        return convertTasksToString(tasks);
        /*
        StringBuilder sb = new StringBuilder();
        this.tasks.forEach(t -> sb.append(this.tasks.indexOf(t) + 1)
                .append(". ").append(t).append("\n"));

        return sb.toString();

         */
    }

    /**
     * Adds a given task to the list.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a given task from the list.
     * @param index The index of the task to be deleted.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Marks a given task as complete.
     * @param index The index of the task to be marked as complete.
     */
    public String markTask(int index) {
        return this.tasks.get(index).mark();
    }

    /**
     * Marks a given task as incomplete.
     * @param index The index of the task to be marked as incomplete.
     */
    public String unmarkTask(int index) {
        return this.tasks.get(index).unmark();
    }

    /**
     * Returns the task list as a string in its saved format.
     * @return The string representation of the saved format of the task list.
     */
    public String getSaveFormat() {
        StringBuilder sb = new StringBuilder();
        this.tasks.forEach(t -> sb.append(t.getSaveFormat()).append("\n"));
        return sb.toString();
    }

    /**
     * Gets the size of the task list.
     * @return The size of the task list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Searches for tasks that matches the given keyword.
     * @param keyword The given keyword.
     * @return A string representing all possible matches.
     */
    public String find(String keyword) {
        List<Task> filteredTasks = filterTasks(task -> task.isMatch(keyword));
        return convertTasksToString(filteredTasks);
    }

    /**
     * Shows all tasks scheduled on a given date.
     * @param date
     * @return The string representation of all tasks on a given date.
     */
    public String viewSchedule(LocalDate date) {
        List<Task> filteredTasks = filterTasks(task -> task.isDateMatch(date));
        return convertTasksToString(filteredTasks);
    }

    /**
     * Filters the task list with the given condition.
     * @param condition
     * @return A list of tasks satisfying the given condition.
     */
    private List<Task> filterTasks(Predicate<Task> condition) {
        return tasks.stream()
                .filter(condition)
                .collect(Collectors.toList());
    }

    /**
     * Converts a given list of tasks to its string representation.
     * @param tasks
     * @return The string representation of a given list of tasks.
     */
    private String convertTasksToString(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        tasks.forEach(task -> sb.append(tasks.indexOf(task) + 1)
                .append(". ")
                .append(task)
                .append("\n"));
        return sb.toString();
    }
}
