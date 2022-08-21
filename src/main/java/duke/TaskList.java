package duke;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import duke.task.Task;

/**
 * A class that handles logging of strings.
 */
public class TaskList {
    private final List<Task> logs;

    public TaskList(List<Task> log) {
        this.logs = new ArrayList<>(log);
    }

    public TaskList() {
        this(new ArrayList<>());
    }

    public int size() {
        return this.logs.size();
    }

    public Task add(Task task) {
        logs.add(task);
        return task;
    }

    public Task delete(int index) throws DukeException {
        try {
            return logs.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(outOfBoundsMessage(index), e);
        }
    }

    Task getTask(int index) throws DukeException {
        try {
            return logs.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(outOfBoundsMessage(index), e);
        }
    }

    public Task markTask(int index) throws DukeException {
        try {
            getTask(index).mark();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(outOfBoundsMessage(index), e);
        }
        return getTask(index);
    }

    public Task unmarkTask(int index) throws DukeException {
        try {
            getTask(index).unmark();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(outOfBoundsMessage(index), e);
        }
        return getTask(index);
    }

    public List<Task> getTasks() {
        return this.logs;
    }

    public List<Task> find(String query) {
        return this.logs.stream()
                .filter((task) -> task.getDescription().contains(query))
                .collect(Collectors.toList());
    }

    private String outOfBoundsMessage(int index) {
        return "Index " + (index + 1) + " out of bounds. There are only " + this.logs.size() + " tasks!";
    }

    @Override
    public String toString() {
        return getTasks().toString();
    }
}
