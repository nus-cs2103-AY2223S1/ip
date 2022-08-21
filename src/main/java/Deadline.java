public class Deadline extends Task {

    private String by;

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String saveStringFormat() {
        return String.format("D | %s | %s", super.saveStringFormat(), by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}