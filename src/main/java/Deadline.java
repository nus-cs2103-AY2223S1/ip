public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String saveString() {
        return "D | " + (this.isDone ? "1 | " : "0 | ") + this.description + " | " + this.by;
    }
    
    @Override
    public String toString() {
        return "[D]" +super.toString() + " (by: " + this.by + ")";
    }

}
