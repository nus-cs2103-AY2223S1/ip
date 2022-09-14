package Duke.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event object that allows users to specify the date and time of the Task.
 */
public class Event extends Task {

    private static final String SHORTHAND = "E";

    private LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description, SHORTHAND);
        this.at = at;
    }

    Event(String description, String at) {
        super(description, SHORTHAND);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT));
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getStorageString() {
        String parentStorageString = super.getStorageString();
        return String.format("%s|%s", parentStorageString, getAtStorage());
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        String parStr = super.toString();
        return String.format("%s (at: %s)", parStr, getAtStr());
    }

    private String getAtStr() {
        return this.at.format(DateTimeFormatter.ofPattern(PRINT_TIME_FORMAT));
    }

    private String getAtStorage() {
        return this.at.format(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT));
    }
}
