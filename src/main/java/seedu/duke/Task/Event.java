package seedu.duke.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected LocalDate date;

    public Event (String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Updates the specified category of this Task
     *
     * @param category the category to be updated
     * @param details the update
     * @return this Task
     */
    @Override
    public Task update (String category, String details) {
        if ("description".equals(category)) {
            this.description = details;
        } else if ("date".equals(category)) {
            this.date = LocalDate.parse(details);
        }
        return this;
    }

    /**
     * Returns the short form of the type of this Task
     *
     * @return the short form of the type of this Task
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Returns the long form of the type of this Task
     *
     * @return the long form of the type of this Task
     */
    @Override
    public String getTypeLong() {
        return "event";
    }

    /**
     * Returns the String object representing this Task
     *
     * @return the String object representing this Task
     */
    @Override
    public String toString() {
        return "[" + this.getType() + "]" + "[" + this.getStatusIcon() + "] " + this.description
                + "(at " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the String object to be stored as data representing this Task
     *
     * @return the String object to be stored as data representing this Task
     */
    @Override
    public String getData() {
        return getTypeLong() + "|" + this.description + "|" + this.getStatus() + "|" + this.date;
    }
}
