public class Deadline extends Task {
    String deadline;

    /**
     * Constructor to create new Deadline
     * 
     * @param description Description of deadline you want to create
     * @param deadline    Deadline of the task
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Factory method to create new Deadline
     * 
     * @param input String including task description and deadline specified after
     *              /by
     * @return New Deadline
     * @throws DukeException if deadline is not specified using /by
     */
    public static Deadline createDeadline(String input) throws DukeException {
        if (input.indexOf("/by ") == -1)
            throw new DukeException("Please enter a valid deadline using the /by flag.");
        String deadlineDescription = input.split("/by ")[0];
        String deadline = input.split("/by ")[1];
        return new Deadline(deadlineDescription, deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.deadline + ")";
    }
}
