public class Deadline extends Task {

    private final String by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
    }

    @Override
    public String encode() {
        return "D | " + super.encode() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
