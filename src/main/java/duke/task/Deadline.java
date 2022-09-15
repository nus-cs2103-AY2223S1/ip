package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline Task
 */
public class Deadline extends Task {
    private String by;

    /**
     * Creates a Deadline Task object
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, h:mma");

        assert !by.isEmpty();

        LocalDateTime dateTime = LocalDateTime.parse(by.trim(), inputFormatter);
        this.by = dateTime.format(outputFormatter);
    }

    /**
     * Creates a Deadline Task object, to be used
     * when loading from task file
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        assert !by.isEmpty();
        this.by = by;
    }

    /**
     * Returns a string describing the Deadline object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string describing the Deadline object
     * to be used when saving to a task file
     */
    @Override
    public String toStringStorage() {
        return "D | " + super.toStringStorage() + " (" + by + ")";
    }
}
