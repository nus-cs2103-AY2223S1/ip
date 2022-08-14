import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A class that handles logging of strings.
 */
public class Log {
    private final List<Task> logs;

    Log(List<Task> log) {
        this.logs = new ArrayList<>(log);
    }

    Log() {
        this(new ArrayList<>());
    }

    int size() {
        return this.logs.size();
    }

    void add(String message) {
        logs.add(new Task(message));
    }

    void add(Task task) {
        logs.add(task);
    }

    Task getTask(int index) {
        return logs.get(index);
    }

    Task markTask(int index) {
        getTask(index).mark();
        return getTask(index);
    }

    Task unmarkTask(int index) {
        getTask(index).unmark();
        return getTask(index);
    }

    List<String> getLogs() {
        List<String> indexedList = IntStream.range(0, logs.size())
                .mapToObj((index) -> String.format("%d. %s", index + 1, logs.get(index).toString()))
                .collect(Collectors.toList());
        return indexedList;
    }

    @Override
    public String toString() {
        return String.join("\n", getLogs());
    }
}
