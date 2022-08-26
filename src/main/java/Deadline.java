public class Deadline extends Task {

    protected String by;
    protected Date date;

    public Deadline(String description, String by) throws DukeInvalidDateException {
        super(description);
        this.by = by;
        this.date = new Date(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }

    @Override
    public String toStringForFile() {
        return super.toStringForFile() + "deadline " + this.description + " /by " + this.by;
    }
}