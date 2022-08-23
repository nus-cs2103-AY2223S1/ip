public class Deadline extends Task {

    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String toFileDescription() {
        return "D" + " | " + super.toFileDescription() + " | " + this.by;
    }

    public static Deadline fromFileDescription(String input) {
        String[] strArray = input.split(" \\| ", 4);
        String description = strArray[2];
        String by = strArray[3];
        Deadline deadline = new Deadline(description, by);
        if (strArray[1].equals("1")) {
            deadline.markDone();
        }
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
