package duke;

import duke.task.Task;
import duke.task.TimedTask;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * List of tasks with methods to add, delete and interact with the tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Creates a new empty list.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Creates a new list with the given tasks.
     * @param tasks List of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Outputs the list of tasks as a List.
     * @return List of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Outputs the number of tasks.
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Outputs the task at this index. Index is one-based.
     * @param index One-based index.
     * @return The task at the given index.
     * @throws DukeException
     */
    public Task get(int index) throws DukeException {
        try {
            return tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\u2639 OOPS!!! Invalid index %s. You only have %d tasks in your list.",
                    index, tasks.size());
        }
    }

    /**
     * Returns the list of tasks that are on the given date.
     * @param date The date to find the tasks from. Must be valid.
     * @return List of tasks.
     * @throws DukeException
     */
    public TaskList getTasksByDate(String date) throws DukeException {
        LocalDate convertedDate;
        try {
            convertedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(TimedTask.format));
        } catch (DateTimeParseException e) {
            throw new DukeException("\u2639 OOPS!!! Wrong date format. Please input date in the format %s.",
                    TimedTask.format);
        }
        return new TaskList(tasks.stream()
                .filter(x -> x instanceof TimedTask && ((TimedTask) x).getTime().toLocalDate().equals(convertedDate))
                .collect(Collectors.toList()));
    }

    public TaskList filter(String keyword) {
        return new TaskList(tasks.stream()
                .filter(x -> x.getDescription().contains(keyword))
                .collect(Collectors.toList())
        );
    }

    /**
     * Adds a new task.
     * @param task Task.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Marks the task at the given index as done.
     * @param index One-based index.
     * @return The marked task at that index.
     * @throws DukeException
     */
    public Task mark(int index) throws DukeException {
        this.get(index).markAsDone();
        return this.get(index);
    }

    /**
     * Marks the task at the given index as not done.
     * @param index One-based index.
     * @return The unmarked task at that index.
     * @throws DukeException
     */
    public Task unmark(int index) throws DukeException {
        this.get(index).markAsNotDone();
        return this.get(index);
    }

    /**
     * Deletes the task at the given index.
     * @param index One-based index.
     * @return The deleted task.
     * @throws DukeException
     */
    public Task delete(int index) throws DukeException {
        try {
            return tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\u2639 OOPS!!! Invalid index %s. You only have %d tasks in your list.",
                    index, tasks.size());
        }
    }

    /**
     * Sorts all tasks.
     */
    public void sort() {
        Collections.sort(tasks);
    }
}
