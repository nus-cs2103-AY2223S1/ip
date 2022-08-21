public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description, "D");
        this.by = by;
    }

    public Deadline(String description, String done, String by) {
        super(description, done, "D");
        this.by = by;
    }

    public String getDateline() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
