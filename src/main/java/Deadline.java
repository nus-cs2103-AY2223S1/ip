public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        if (description.isBlank() || by.isBlank()) {
            throw new DukeException("The description of deadline task is missing information!");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
