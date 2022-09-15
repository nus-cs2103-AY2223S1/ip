package bro.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bro.BroException;
import bro.Parser;

/**
 * Event class.
 */
public class Event extends Task {
    protected LocalDateTime atStore;
    protected String at;
    protected boolean isMonthFormat;

    /**
     * Constructor of Event class.
     * @param description The title of the task to be done.
     * @param at Time of the task to be done.
     * @throws BroException If the time format is invalid.
     */
    public Event(String description, String at) throws BroException {
        super(description);
        this.at = at;
        isMonthFormat = false;
        if (at.trim().split(" ").length == 4) {
            isMonthFormat = true;
        } else {
            atStore = Parser.timeParser(at);
        }
    }
    @Override
    public String toString() {
        if (isMonthFormat) {
            return "[E]" + super.toString() + " (at: " + at + ")";
        } else {
            return "[E]" + super.toString() + " (at: "
                    + atStore.format(DateTimeFormatter.ofPattern("MMM dd yyyy hhmm")) + ")";
        }
    }
}
