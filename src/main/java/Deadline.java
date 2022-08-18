public class Deadline extends Task{

    protected String by;

    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
