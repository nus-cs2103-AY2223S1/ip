public class Deadline extends Task {
    public String deadline;

    public Deadline(String details, String deadline) {
        super(details);
        this.deadline = deadline;
    }

    @Override
    public String getTaskIcon() {
        return "D";
    }

    @Override
    public String getDetails() {
        return details + " (by: " + deadline + ")";
    }
}
