import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A class that handles logging of strings.
 */
public class TaskList {
    private final List<Task> logs;

    TaskList(List<Task> log) {
        this.logs = new ArrayList<>(log);
    }

    TaskList() {
        this(new ArrayList<>());
    }

    int size() {
        return this.logs.size();
    }

    Task add(String message) {
        Task task = new Task(message);
        logs.add(task);
        return task;
    }

    Task add(Task task) {
        logs.add(task);
        return task;
    }

    Task delete(int index) throws DukeException {
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

    Task markTask(int index) throws DukeException {
        try {
            getTask(index).mark();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(outOfBoundsMessage(index), e);
        }
        return getTask(index);
    }

    Task unmarkTask(int index) throws DukeException {
        try {
            getTask(index).unmark();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(outOfBoundsMessage(index), e);
        }
        return getTask(index);
    }

    List<String> getLogs() {
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
