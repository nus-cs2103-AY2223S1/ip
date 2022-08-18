public class Deadline extends Task {
    protected String deadline;
    /**
     * Construct Task with a fixed name.
     *
     * @param name The name of the task.
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Shows the deadline name and status as a String.
     *
     * @return A String with the task name and status.
     */
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
