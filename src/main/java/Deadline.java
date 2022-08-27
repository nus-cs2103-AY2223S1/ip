public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toSave() { return "D | " + super.toSave() + "| " + by; }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "BY: " + by + "!";
    }
}
