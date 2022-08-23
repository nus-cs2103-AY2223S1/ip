public class Deadline extends Task {
    protected String endDate;

    public Deadline(String description, String endDate) {
        super(description);
        this.endDate = endDate;
    }

    @Override
    public String getTask() {
        String done = this.isDone ? "1" : "0";
        return String.format("D | %s | %s | %s", done, this.description, this.endDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.endDate + ")";
    }
}
