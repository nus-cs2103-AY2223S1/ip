public class Deadline extends Task {
    protected String by;

    public Deadline(String description, Boolean isDone, String by) {
        super(description);
        if (isDone) {
            super.markAsDone();
        }
        this.by = by;
    }

    @Override
    public String saveData() {
        String isDone;
        if (super.isDone) {
            isDone = "O";
        } else {
            isDone = "X";
        }
        return String.format("D | %s | %s | %s\n", isDone, super.description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
