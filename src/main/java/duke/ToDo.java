package duke;

import java.time.LocalDate;

/**
 * An abstraction for a task that has to be done.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public LocalDate getDate() {
        return null;
    }

    @Override
    public int getPeriod() {
        return 0;
    }

    @Override
    public void updateDate() {
        return;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
