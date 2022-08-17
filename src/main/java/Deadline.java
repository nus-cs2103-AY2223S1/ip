public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String completionString = this.isDone ? "[D][x]" : "[D][ ]";
        return completionString + " " + this.description + " (by: " + this.by + ")";
    }
}
