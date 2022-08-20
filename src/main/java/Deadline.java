public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toFileRepresentation() {
        return String.format("D | %s | %s", super.toFileRepresentation(), this.by);
    }

    public static Deadline fromFileRepresentation(String rep) {
        String[] args = rep.split(" \\| ");
        boolean isDone = args[1].equals("1");
        String description = args[2];
        String date = args[3];
        Deadline result = new Deadline(description, date);
        if (isDone) {
            result.markDone();
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.by);
    }
}
