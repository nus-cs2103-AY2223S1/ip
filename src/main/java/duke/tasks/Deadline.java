package duke.tasks;

/**
 * Represents deadlines in the task list, storing the description of the deadline and the deadline itself as string
 */
public class Deadline extends Task {

    protected String deadline;

    /**
     * Constructor of deadline
     * @param description description of deadline task
     * @param deadline date signifying deadline of task
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLetterTag() {
        return "D";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAdditionalInfo() {
        return this.deadline;
    }

    /**
     * Returns properly formatted string to be displayed to user
     * @return String formatted for user to read
     */
    @Override
    public String toString() {
        return "[" + this.getLetterTag() + "][" + this.getStatusIcon() + "] "
                + this.description + " (by: " + this.deadline + ")";
    }

}
