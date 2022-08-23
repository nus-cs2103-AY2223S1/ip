public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean done) {
        super(description);
        this.by = by;
        this.isDone = done;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "+ by + ")";
    }

    @Override
    public String save() {
        return "D | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.by;
    }
}
