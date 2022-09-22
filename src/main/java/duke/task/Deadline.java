package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class that encapsulates the Deadline task.
 */
public class Deadline extends Task {

    /** The deadline of the task */
    private final LocalDate deadline;

    /**
     * The class constructor.
     *
     * @param description The description of the deadline task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
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
        return "D|" + statusString + super.description + "|" + this.deadline;
    }

    /**
     * Returns the date associated with the task.
     *
     * @return The date associated with the task.
     */
    @Override
    public LocalDate getDate() {
        return this.deadline;
    }


    /**
     * Returns the string representation of the deadline task.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        String date = this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: "
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
            return this.deadline.compareTo(taskDate);
        }
    }
}
