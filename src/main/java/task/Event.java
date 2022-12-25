package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Event object that allows users to specify the date and time of the Task.
 */
public class Event extends Task {

    private static final String SHORTHAND = "E";

    private LocalDateTime datetime;

    /**
     * Initialises the Event with the given description, datetime, and no tags.
     *
     * @param description The description of the Event.
     * @param datetime The datetime of the Event.
     */
    public Event(String description, LocalDateTime datetime) {
        this(description, datetime, new ArrayList<>());
    }

    /**
     * Initialises the Event with the given description, datetime, tags.
     *
     * @param description The description of the Event.
     * @param datetime The datetime of the Event.
     * @param tags A list of tags for this Event.
     */
    public Event(String description, LocalDateTime datetime, List<String> tags) {
        super(description, SHORTHAND);
        assert datetime != null: "Timing for Events cannot be null";
        this.datetime = datetime;
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
        String tags = super.getPrintableTags();
        if (!tags.equals("")) {
            tags = "[" + tags + "]";
        }
        return String.format("%s (at: %s) %s", parStr, getAtStr(), tags);
    }

    private String getAtStr() {
        return this.datetime.format(DateTimeFormatter.ofPattern(PRINT_TIME_FORMAT));
    }

    private String getAtStorage() {
        return this.datetime.format(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT));
    }
}
