package froggy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An Event class which specifies a event task.
 */
public class Event extends Task {
    /* When the task is due. */
    protected LocalDate dateAndTime;
    private final String taskLabel = "E";


    /**
     * Creates an Event object.
     *
     * @param description The description of the task.
     * @param dateAndTime The specific deadline of when the task is due.
     */
    public Event(String description, String dateAndTime) {
        super(description);
        this.dateAndTime = LocalDate.parse(dateAndTime);
    }

    /**
     * Creates an Event object.
     *
     * @param description The description of the task.
     * @param dateAndTime The specific deadline of when the task is due.
     * @param isDone Whether the task has been completed.
     *
     */
    public Event(String description, String dateAndTime, boolean isDone) {
        super(description, isDone);
        this.dateAndTime = LocalDate.parse(dateAndTime);
    }

    /**
     * Returns a string representation of an Event object.
     *
     * @return A string representation of an Event object in the format of [E] do this (at: Oct 15 2019)
     */
    @Override
    public String toString() {
        return "[" + taskLabel + "]" + super.toString() + " (at: "
                + dateAndTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a string representation of an Event object for the purpose of being written to the file.
     *
     * @return A string representation of an Event object in the format of E , do this , 2019-10-15
     */
    public String toFileString() {
        return taskLabel + " , " + super.toFileString() + " , " + dateAndTime;
    }

    @Override
    public void setDateAndTime(LocalDate dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}
