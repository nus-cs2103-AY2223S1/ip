import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * A TaskList stores a list of tasks.
 */
public class TaskList {
    private final List<Task> list = new ArrayList<>();

    /**
     * Adds a task to the list.
     *
     * @param task a string value of the task to add
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Returns the task at the given index.
     *
     * @param index the index of the task to return
     * @return the task at the given index
     */
    public Task getTask(int index) {
        return list.get(index);
    }

    /**
     * Enumerates the tasks stored in this TaskList.
     *
     * @return the enumerated list of tasks
     */
    @Override
    public String toString() {
        return IntStream.range(0, list.size())
                .mapToObj(i -> String.format("%d. %s\n", i + 1, list.get(i)))
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString()
                .trim();
    }
}
