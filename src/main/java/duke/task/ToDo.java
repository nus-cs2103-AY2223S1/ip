package duke.task;

/**
 * TodoTask
 */
public class ToDo extends Task {

    /**
     * Contructor for Todotask.
     *
     * @param description TodoTask description
     * @param status Status of the todotask, 'X' is done, otherwise undone.
     */
    public ToDo(String description, char status) {
        super(description);
        assert status == 'X' || status == ' ' : "The task status should be either X or empty";
        if (status == 'X') {
            super.mark();
        }
    }

    /**
     * Get the description of the todotask.
     *
     * @return The description of the todotask.
     */
    @Override
    public String getDescription() {
        return "[T]" + super.getDescription();
    }

    /**
     * The string representation of todotask.
     *
     * @return String representation of todotask.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
