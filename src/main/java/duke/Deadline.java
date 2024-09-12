
package duke;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the deadline task.
 */
public class Deadline extends Task{
    protected LocalTime timing;
    protected LocalDate date;
    protected boolean isDone;

    /**
     * Constructs a deadline object.
     */
    public Deadline(String description, LocalDate date, LocalTime timing, boolean isDone) {
        super(description);
        this.timing = timing;
        this.date = date;
        this.isDone = isDone;
    }

    @Override
    public String date() {
        return this.date.toString();
    }

    @Override
    public String timing() {
        return this.timing.toString();
    }

    /**
     * Returns the string representation of whether the task is one or not.
     *
     * @return cross represents done, otherwise blank space means not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "O"); // mark done task with X
    }

    /**
     * Marks a specific task object as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a specific task object as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }


    /**
     * Returns string representation of the task.
     *
     * @return String representation of this task.
     */
    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "]" +  super.toString() + " (by: " +
                this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.timing.toString() + ")";
    }
}
