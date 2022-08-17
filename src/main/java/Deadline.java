public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        if (description.isBlank()) {
            throw new DukeException("Take me seriouslyy :( What do you want to do?\n");
        }
        if (by.isBlank()) {
            throw new DukeException("When do you want to get it done??\n");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
