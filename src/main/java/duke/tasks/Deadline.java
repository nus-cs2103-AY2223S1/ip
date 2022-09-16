package duke.tasks;

import java.time.LocalDate;

/**
 * This class represents a deadline. It contains
 * the description and the time the task is due.
 */
public class Deadline extends Task {

    private final LocalDate localDate;

    public Deadline(String name, LocalDate date) {
        super(name);
        localDate = date;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + localDate.toString() + ")";
    }

    @Override
    public String toDataString() {
        return String.format("[D] | %d | %s | %s",
                isMarked() ? 1 : 0,
                getName(),
                localDate.toString());
    }

}
