/**
 * Deadline Class that encapsulates details of a task with a deadline.
 *
 * @author Elgin Lee
 */
public class Deadline extends Task {
    /** The date of deadline. */
    protected String date;

    /**
     * Constructor of Deadline.
     *
     * @param taskName The Task's Name.
     * @param date The date of deadline.
     */
    public Deadline(String taskName, String date) {
        super(taskName);
        this.date = date;
    }

    /**
     * Constructor of Deadline.
     *
     * @param taskName The Task's name.
     * @param date The date of deadline.
     * @param isDone True if the task is done, false otherwise.
     */
    public Deadline(String taskName, String date, boolean isDone) {
        super(taskName, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + date + ")";
    }
}
