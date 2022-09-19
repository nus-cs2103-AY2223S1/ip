package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline object that allows users to set a date and time as a deadline for the
 * Task to be completed.
 */
public class Deadline extends Task {

    private static final String SHORTHAND = "D";

    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description, SHORTHAND);
        assert by != null: "Timing for Deadlines cannot be null";
        this.by = by;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getStorageString() {
        String parentStorageString = super.getStorageString();
        return String.format("%s|%s", parentStorageString, getByStorage());
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        String parStr = super.toString();
        return String.format("%s (by: %s)", parStr, getByStr());
    }

    private String getByStr() {
        return this.by.format(DateTimeFormatter.ofPattern(PRINT_TIME_FORMAT));
    }

    private String getByStorage() {
        return this.by.format(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT));
    }
}
