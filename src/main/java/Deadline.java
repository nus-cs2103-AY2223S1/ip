public class Deadline extends Task {
    private String by;

    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Deadline taskFromSave(String saveString) {
        String[] tokens = saveString.split(" \\| ");
        Deadline deadline = new Deadline(tokens[2], tokens[3]);
        if (tokens[1].equals("1")) {
            deadline.markDone();
        }
        return deadline;
    }
    
    @Override
    public String saveString() {
        return "D | " + super.saveString() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
