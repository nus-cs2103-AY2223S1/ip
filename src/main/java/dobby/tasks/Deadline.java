package dobby.tasks;

/**
 * Deadline is a class which is a task to be done by certain date.
 */
public class Deadline extends Task {
    private String date;

    /**
     * Constructor of the Deadline class.
     *
     * @param task Description of deadline task
     * @param date Date of deadline task
     */
    public Deadline(String task, String date) {
        super(task);
        this.date = date;
    }

    /**
     * Constructor of the Deadline class.
     *
     * @param task Description of deadline task
     * @param date Date of deadline task
     * @param isDone Completion status of deadline task
     */
    public Deadline(String task, String date, boolean isDone) {
        super(task, isDone);
        this.date = date;
    }

    /**
     * Returns the formatted Deadline task to be saved in the file.
     *
     * @return String of deadline task.
     */
    @Override
    public String toPrint() {
        return "D" + super.toPrint() + " | " + date;
    }

    /**
     * Returns the formatted Deadline task to be printed out.
     *
     * @return String of deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
