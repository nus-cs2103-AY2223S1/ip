package duke;

import java.util.List;
import java.util.ArrayList;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.task.Task;

/**
 * A class that handles logging of Tasks.
 */
public class TaskList {
    private final List<Task> logs;

    /**
     * Constructor for TaskList.
     * 
     * @param tasks Initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.logs = new ArrayList<>(tasks);
    }

    /**
     * Constructor for TaskList without an initial list of tasks.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Return number of tasks in TaskList.
     * 
     * @return number of tasks.
     */
    public int size() {
        return this.logs.size();
    }

    /**
     * Add task to list.
     * 
     * @param task task to add.
     * @return Task which was added.
     */
    public Task add(Task task) {
        logs.add(task);
        return task;
    }

    /**
     * Delete task at specified index from list.
     * 
     * @param index index of task to delete.
     * @return Task which was deleted
     * @throws DukeException when index specified is out of range.
     */
    public Task delete(int index) throws DukeException {
        try {
            return logs.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(outOfBoundsMessage(index), e);
        }
    }

    /**
     * Returns task at specified index.
     * 
     * @param index index of task.
     * @return Task at specified index.
     * @throws DukeException when index specified is out of range.
     */
    Task getTask(int index) throws DukeException {
        try {
            return logs.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(outOfBoundsMessage(index), e);
        }
    }

    /**
     * Mark task at index as complete.
     * 
     * @param index index of task to mark.
     * @return Updated Task that was marked.
     * @throws DukeException when index specified is out of range.
     */
    public Task markTask(int index) throws DukeException {
        try {
            getTask(index).mark();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(outOfBoundsMessage(index), e);
        }
        return getTask(index);
    }

    /**
     * Mark task at index as incomplete.
     * 
     * @param index index of task to unmark.
     * @return Updated Task that was unmarked.
     * @throws DukeException when index specified is out of range.
     */
    public Task unmarkTask(int index) throws DukeException {
        try {
            getTask(index).unmark();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(outOfBoundsMessage(index), e);
        }
        return getTask(index);
    }

    /**
     * Get a list of strings that corresponding to the list of tasks.
     * Each string is prefixed with their corresponding index in the list.
     * 
     * @return List of task strings.
     */
    public List<String> getLogs() {
        List<String> indexedList = IntStream.range(0, logs.size())
                .mapToObj((index) -> String.format("%d. %s", index + 1, logs.get(index).toString()))
                .collect(Collectors.toList());
        return indexedList;
    }

    private String outOfBoundsMessage(int index) {
        return "Index " + (index + 1) + " out of bounds. There are only " + this.logs.size() + " tasks!";
    }

    @Override
    public String toString() {
        return String.join("\n", getLogs());
    }
}
