package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate at;

    /**
     * Constructor for an event that takes in the task description and the event date
     * @param description the specifics of the task
     * @param at the date in yyyy-MM-dd that the task is on
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * stringify is used to store the tasks in a standard format in a storage file
     * @return string representation of event to be stored
     */
    @Override
    public String stringify() {
        return String.format("E##%s##%s", super.stringify(), this.at);
    }

    /**
     * toString is used to print out the task in an easily readable format
     * @return string representation of an event
     */
    @Override
    public String toString() {
        return String.format("[E]%s(at: %s)", super.toString(), this.at.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }
}