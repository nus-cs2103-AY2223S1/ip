package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class that encapsulates the Event task.
 */
public class Event extends Task {

    /** The date of the event */
    private final LocalDate date;

    /**
     * The class constructor.
     *
     * @param description The description of the event.
     * @param date The date of the event.
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns the string to be saved in the text file to be
     * loaded in later.
     *
     * @return The string identifying the details of the task.
     */
    @Override
    public String stringToSave() {
        String statusString = "X".equals(super.getStatusIcon()) ? "1|" : "0|";
        return "E|" + statusString + super.description + "|" + this.date;
    }

    /**
     * Returns the date associated with the task.
     *
     * @return The date associated with the task.
     */
    @Override
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        String date = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: "
                + date + ")";
    }

    /**
     * Compares this task with the specified task for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * @param task The task to be compared.
     * @return A negative integer, zero, or a positive integer as this object
     *         is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Task task) {
        LocalDate taskDate = task.getDate();
        if (taskDate == null) {
            return -1;
        } else {
            return this.date.compareTo(taskDate);
        }
    }
}
