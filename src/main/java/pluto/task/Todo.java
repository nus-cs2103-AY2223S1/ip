package pluto.task;

import java.time.LocalDate;

/**
 * Todo task.
 */
public class Todo extends Task {

    /**
     * Constructor that initializes global variables.
     * @param description Task description.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFile() {
        int done = (isDone ? 1 : 0);
        return String.format("T | %d | %s", done, description);
    }

    @Override
    public LocalDate getDateMaybe() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Todo) {
            Todo other = (Todo) o;
            return this.description.equals(other.description) && this.isDone == other.isDone;
        }
        return false;
    }
}
