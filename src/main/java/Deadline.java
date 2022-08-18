public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String date) {
        super(description);
        this.deadline = date;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline + ")";
    }
}
