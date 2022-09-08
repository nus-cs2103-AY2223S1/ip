package Duke;

import java.time.LocalDate;

/**
 * Represents a task to be done.
 */
public class ToDo extends Task {
    public ToDo(String description, LocalDate date) {
        super(description, date);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
