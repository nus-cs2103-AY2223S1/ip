import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;

import org.apache.commons.text.WordUtils;

public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    /**
     * Adds a new Task with a given description to the TaskList.
     *
     * @param taskType The string representing the type of Task to be added.
     * @param args The map of strings representing the arguments to be supplied to the constructor.
     */
    public void addTask(String taskType, Map<String, String> args) {
        add(Task.of(taskType, args));
    }

    /**
     * Marks a Task as done or undone.
     *
     * @param index The index of the Task to be marked.
     * @param isDone Whether the Task is to be marked as done or undone.
     */
    public void setDone(int index, boolean isDone) {
        get(index).setDone(isDone);
    }

    /**
     * Returns the string representation of the length of the TaskList.
     *
     * @return The string representing the length of the TaskList.
     */
    public String lengthString() {
        return size() == 1
                ? String.format("%d task", size())
                : String.format("%d tasks", size());
    }

    /**
     * Returns the string representation of a TaskList.
     *
     * @return The string representing the TaskList.
     */
    @Override
    public String toString() {
        AtomicInteger i = new AtomicInteger(1);
        return stream().map(task -> WordUtils.wrap(String.format("%d. %s\n", i.getAndIncrement(), task),
                        40, "\n   ", false))
                .reduce("", String::concat);
    }
}
