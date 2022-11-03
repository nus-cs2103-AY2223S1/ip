package duke;

import java.time.LocalDate;

/**
 * Represents a task to be done.
 */
public class ToDo extends Task {
    public ToDo(String description, LocalDate date) {
        super(description, date);
        assert date == null : "A ToDo should not have a date";
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
