public class Deadline extends Task {

    // Attributes of a Deadline
    protected String by;

    /**
     * The constructor for the Deadline
     * @param description the description of the task
     * @param by the deadline of the task
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        if (description.equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
    }

    /**
     * The string representation of the deadline
     * @return a string representing the description and the due date of the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * A string representation to write to the file
     * @return a string that represents what to write to the file
     */
    public String saveToDisk() {
        String alreadyDone = super.getStatusIcon();
        return "D | " + alreadyDone + " | " + super.getDescription() + "\n";
    }
}
