import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.text.WordUtils;

public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    /**
     * Adds a new Task with a given description to the TaskList.
     * @param description The string representing the description of the Task to be added.
     */
    public void addTask(String description) {
        this.add(new Task(description));
    }

    /**
     * Marks a Task as done or undone.
     * @param index The index of the Task to be marked.
     * @param isDone Whether the Task is to be marked as done or undone.
     */
    public void setDone(int index, boolean isDone) {
        this.get(index).setDone(isDone);
    }

    /**
     * Returns the string representation of a TaskList.
     *
     * @return The string representing the TaskList.
     */
    @Override
    public String toString() {
        AtomicInteger i = new AtomicInteger(1);
        return this.stream().map(task -> WordUtils.wrap(String.format("%d. %s\n", i.getAndIncrement(), task),
                        40, "\n   ", false))
                .reduce("", String::concat);
    }
}
