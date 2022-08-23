public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Deadline parseFile(String string) {
        String[] details = string.split(" \\| ");
        Deadline deadline = new Deadline(details[2], details[3]);
        if (details[1].equals("1")) {
            deadline.markAsDone();
        }
        return deadline;
    }

    @Override
    public String toDataFormat() {
        String completed = "0";
        if (this.getStatusIcon().equals("X")) {
            completed = "1";
        }
        return "D | " + completed + " | " + this.getDescription() + " | " + this.by;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}