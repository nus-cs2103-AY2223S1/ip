import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    /**
     * Method that adds a new Task with a given description to the TaskList.
     * @param description The string representing the description of the Task to be added.
     */
    public void addTask(String description) {
        this.add(new Task(description));
    }

    /**
     * Returns the string representation of a TaskList.
     *
     * @return The string representing the TaskList.
     */
    @Override
    public String toString() {
        AtomicInteger i = new AtomicInteger(1);
        return this.stream().map(task -> String.format("%d. %s\n", i.getAndIncrement(), task))
                .reduce("", String::concat);
    }
}
