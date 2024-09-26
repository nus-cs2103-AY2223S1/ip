package froggy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline class which specifies a deadline Task.
 */
public class Deadline extends Task {
    /* When the task is due. */
    protected LocalDate dateAndTime;
    private final String taskLabel = "D";

    /**
     * Creates a Deadline object.
     *
     * @param description The description of the task.
     * @param dateAndTime The specific deadline of when the task is due.
     */
    public Deadline(String description, String dateAndTime) {
        super(description);
        this.dateAndTime = LocalDate.parse(dateAndTime);
    }

    /**
     * Creates a Deadline object.
     *
     * @param description The description of the task.
     * @param dateAndTime The specific deadline of when the task is due.
     * @param isDone Whether the task has been completed.
     */
    public Deadline(String description, String dateAndTime, boolean isDone) {
        super(description, isDone);
        this.dateAndTime = LocalDate.parse(dateAndTime);
    }

    /**
     * Returns a string representation of a Deadline object.
     *
     * @return A string representation of a Deadline object in the format of [D] do this (by: Oct 15 2019)
     */
    @Override
    public String toString() {
        return "[" + taskLabel + "]" + super.toString() + " (by: "
                + dateAndTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a string representation of a Deadline object for the purpose of being written to the file.
     *
     * @return A string representation of a Deadline object in the format of D , do this , 2019-10-15
     */
    public String toFileString() {
        return taskLabel + " , " + super.toFileString() + " , " + dateAndTime;
    }

    @Override
    public void setDateAndTime(LocalDate dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}
