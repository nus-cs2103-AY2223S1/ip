public class Deadline extends Task {
    private String endDate;

    public Deadline(String taskName, String endDate) {
        super(taskName);
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.endDate + ")";
    }
}
