package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.Task;

/**
 * Represents an Event task
 */
public class Event extends Task {
    private LocalDate date;

    /**
     * Initialzs event instance
     * @param item string entered by user
     * @param dateTime date entered by user
     */
    public Event(String item, LocalDate date) {
        this.setItem(item);
        this.date = date;
    }

    /**
     * Gets the string representation of the task
     * @return string representation of the task
     */
    public String getTask() {
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[E] " + this.getStatusIcon() + " " + this.getItem() + " (at: " + formattedDate + ")";
    }

    /**
     * Gets the representation of the task that is stored in the data file
     * @return string representation of the task that is stored in the data file
     */
    public String getFileLine() {
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[E]" + "##" + this.getStatusIcon() + "##" + this.getItem() + "##" + formattedDate;
    }

    /**
     * Returns date in string format
     * @return date in string format
     */
    public LocalDate getDate() {
        return this.date;
    }
}
