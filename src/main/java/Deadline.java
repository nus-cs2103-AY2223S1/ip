public class Deadline extends Task {
    private String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String formatTask() {
        return String.format("[D] [%s] %s (by %s) \n", this.getStatusIcon(), this.description, deadline);
    }
}
