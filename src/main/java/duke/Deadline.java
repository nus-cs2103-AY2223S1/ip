
package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * It is a class extending Task class
 */
public class Deadline extends Task{
    protected String timing;
    protected String date;
    protected boolean isDone;

    /**
     * Constructor that instantiates a Deadline object
     */
    public Deadline(String description, String date, String timing, boolean isDone) {
        super(description);
        this.timing = timing;
        this.date = date;
        this.isDone = isDone;
    }

    @Override
    public String date() {
        return this.date;
    }

    @Override
    public String timing() {
        return this.timing;
    }

    /**
     * tells us if a particular task is done or not.
     * @return cross represents done, otherwise blank space means not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "O"); // mark done task with X
    }

    /**
     * marks a specific task object as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * marks a specific task object as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * returns String representation.
     * @return String representation of Task
     */


    /**
     * Returns string representation of the task
     * @return String representation of this task
     */
    @Override
    public String toString() {
        LocalDate d1 = LocalDate.parse(this.date);
        return "[D]" + "[" + this.getStatusIcon() + "]" +  super.toString() + " (by: " +
                d1.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.timing + ")";
    }
}
