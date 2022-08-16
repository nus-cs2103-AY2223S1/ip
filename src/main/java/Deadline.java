public class Deadline extends Task {
    private String by;

    public Deadline(String item, String by) {
        super(item);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
