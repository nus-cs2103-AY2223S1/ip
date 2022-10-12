package duke.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.commons.text.WordUtils;

public class TaskList extends ArrayList<Task> implements Serializable {

    public TaskList() {
        super();
    }

    /**
     * Marks a {@code Task} as done or undone.
     *
     * @param index The index of the {@code Task} to be marked.
     * @param isDone Whether the {@code Task} is to be marked as done or undone.
     */
    public void setDone(int index, boolean isDone) {
        assert !(index < 0 || index >= size());
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
     * Returns a new {@code TaskList} of every {@code Task} with
     * description containing the specified keyword.
     *
     * @param keyword The {@code String} to search the {@code TaskList} with.
     * @return A new {@code TaskList} of every {@code Task} with description
     *         containing the specified keyword.
     */
    public TaskList findByKeyword(String keyword) {
        assert !keyword.isBlank();
        return stream().filter(task -> task.contains(keyword))
                .collect(Collectors.toCollection(TaskList::new));
    }

    public void sort(String sortType, boolean isAscending) {
        switch (sortType) {
        case "a":
        case "alphabet":
        case "alphabetically":
            this.sort(Comparator.comparing(Task::getDescription));
            break;
        case "t":
        case "time":
        case "chronologically":
        default:
            this.sort(Comparator.comparing(Task::getTime));
        }
        if (!isAscending) {
            Collections.reverse(this);
        }
    }

    /**
     * Returns the string representation of a {@code TaskList}.
     *
     * @return The string representing the {@code TaskList}.
     */
    @Override
    public String toString() {
        AtomicInteger i = new AtomicInteger(1);
        return stream().map(task -> WordUtils.wrap(String.format("%d. %s\n", i.getAndIncrement(), task),
                        65, "\n   ", false))
                .reduce("", String::concat);
    }
}
