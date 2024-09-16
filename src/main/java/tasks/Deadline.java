package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Task with a date to complete the task by
 */
public class Deadline extends Task {
    /**
     * Deadline object by field which indicates the deadline
     */
    protected LocalDate byDate;

    /**
     * A constructor to intialize the Deadline object with the description and deadline
     *
     * @param description The task
     * @param byDate The deadline
     */
    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDate = byDate;
    }

    /**
     * Returns a string that represents the Deadline
     *
     * @return String A string that represents the current object
     */
    @Override
    public String toString() {
        String date = this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D][" + this.getStatusIcon() + "] " + super.toString() + " (by: " + date + ")";
    }

    /**
     * Returns a string that represents the Deadline in the format for the textfile
     *
     * @return String A string that represents the current object
     */
    @Override
    public String toFileString() {
        String date = this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "D | " +  this.getFileStatus() + " | " + super.toString() + " | " + date;
    }

    /**
     * Changes the date of the Deadline
     *
     * @param date The new date
     */
    public void changeDate(LocalDate date){
        this.byDate = date;
    }

}

