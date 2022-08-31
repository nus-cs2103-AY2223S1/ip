package duke.task;

/**
 * Represents a Deadline task; subclass of a Task.
 * @author Huang Yuchen
 * @author hyuchen@u.nus.edu
 */
public class Deadline extends Task {
    private final String deadline;

    /**
     * Constructor for the Deadline class.
     *
     * @param taskName the name of the deadline task
     * @param deadline the deadline of the task
     */
    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * Returns a reformatted string of the task to be stored in the text file.
     *
     * @return Reformatted string representation of the task
     */
    @Override
    public String formatTask() {
        return String.format("D | %s | %s | %s\n", (this.getIsDone() ? "1" : "0"), this.getTaskName(), this.deadline);
    }

    /**
     * Returns a string of the task (eg.: [D][ ] return book (by: June 6th) ).
     *
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
