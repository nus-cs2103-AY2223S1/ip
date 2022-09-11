package sus.task;

import java.time.LocalDate;

import sus.common.Utils;

/**
 * Represents an Event.
 */
public class Event extends Task {

    private final LocalDate timeFrame;

    /**
     * Constructor for a new Event.
     *
     * @param description description of the event
     * @param timeFrame time frame of the event
     */
    public Event(String description, LocalDate timeFrame) {
        super(description);
        this.timeFrame = timeFrame;
    }

    @Override
    public String encodeToString() {
        return String.format("E | %s | %s | %s", (isDone ? "1" : "0"), description, timeFrame);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), Utils.convertLocalDateToString(timeFrame));
    }
}
