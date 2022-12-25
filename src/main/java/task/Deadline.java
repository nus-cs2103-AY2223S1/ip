package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Deadline object that allows users to set a date and time as a deadline for the
 * Task to be completed.
 */
public class Deadline extends Task {

    private static final String SHORTHAND = "D";

    private LocalDateTime datetime;

    /**
     * Initialises the Deadline with the given description, datetime, and no tags.
     *
     * @param description The description of the Deadline.
     * @param datetime The datetime of the Deadline.
     */
    public Deadline(String description, LocalDateTime datetime) {
        this(description, datetime, new ArrayList<>());
    }

    /**
     * Initialises the Deadline with the given description, datetime, tags.
     *
     * @param description The description of the Deadline.
     * @param datetime The datetime of the Deadline.
     * @param tags A list of tags for this Deadline.
     */
    public Deadline(String description, LocalDateTime datetime, List<String> tags) {
        super(description, SHORTHAND, tags);
        assert datetime != null: "Timing for Deadlines cannot be null";
        this.datetime = datetime;
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
        String tags = super.getPrintableTags();
        if (!tags.equals("")) {
            tags = "[" + tags + "]";
        }
        return String.format("%s (by: %s) %s", parStr, getByStr(), tags);
    }

    private String getByStr() {
        return this.datetime.format(DateTimeFormatter.ofPattern(PRINT_TIME_FORMAT));
    }

    private String getByStorage() {
        return this.datetime.format(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT));
    }
}
