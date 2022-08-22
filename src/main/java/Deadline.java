public class Deadline extends Task {

    private final String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String stringToSave() {
        return "D|" + ("X".equals(super.getStatusIcon()) ? "1|" : "0|") + super.description + "|" + this.deadline;
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + this.deadline + ")";
    }
}
