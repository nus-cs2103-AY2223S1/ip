package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline Class that encapsulates details of a task with a deadline.
 *
 * @author Elgin Lee
 */
public class Deadline extends Task {
    /** The date of deadline. */
    private LocalDate date;

    /** The time of deadline. */
    private LocalTime time;

    /**
     * Constructor of Deadline.
     *
     * @param taskName The Task's Name.
     * @param date The date of deadline.
     * @param time The time of deadline
     */
    public Deadline(String taskName, String date, String time) {
        super(taskName);
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
    }

    /**
     * Constructor of Deadline.
     *
     * @param taskName The Task's name.
     * @param date The date of deadline.
     * @param time The time of deadline.
     * @param isDone True if the task is done, false otherwise.
     */
    public Deadline(String taskName, String date, String time, boolean isDone) {
        super(taskName, isDone);
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
    }

    /**
     * Gets the date of Deadline.
     *
     * @return Date of deadline.
     */
    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    /**
     * String representation of a Deadline.
     *
     * @return Deadline representation.
     */
    @Override
    public String toString() {
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedTime = this.time.format(DateTimeFormatter.ofPattern("h:mma"));

        // Basic task representation, mark status and task description (e.g. [X] sleep).
        String basicDescription = super.toString();

        return "[D]" + basicDescription + " (by: " + formattedDate + ", " + formattedTime + ")";
    }
}
