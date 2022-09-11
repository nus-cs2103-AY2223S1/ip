package duke.task;

import static duke.parser.Parser.DATE_TIME_OUTPUT_FORMAT;
import static duke.parser.Parser.DATE_TIME_SAVE_FORMAT;

import java.time.LocalDateTime;

/**
 * Encapsulates an event.
 */
public class EventTask extends Task {
    private final LocalDateTime time;

    /**
     * Constructs a new {@code EventTask} with given description and time.
     *
     * @param description The description of the event.
     * @param time The time of the event.
     */
    public EventTask(String description, LocalDateTime time) {
        super(TaskSymbolType.E, description);
        this.time = time;
    }

    /**
     * Returns the string representation of this {@code EventTask} for display.
     *
     * @return The string representation of this {@code EventTask} for display.
     */
    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.time.format(DATE_TIME_OUTPUT_FORMAT));
    }

    /**
     * Returns the string representation of the event task for storage.
     *
     * @return The string representation of the event task for storage.
     */
    @Override
    public String toSaveString() {
        return String.format("%s | %s", super.toSaveString(), this.time.format(DATE_TIME_SAVE_FORMAT));
    }

    /**
     * Checks if an {@code Object} is same as this {@code EventTask}.
     *
     * @param o The {@code Object} to check.
     * @return {@code true} if the {@code Object} is same as this {@code EventTask}, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        EventTask that = (EventTask) o;
        return time.equals(that.time);
    }
}
