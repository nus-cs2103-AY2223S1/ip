public class Deadline extends Task {
    private String dateTime;

    public Deadline(String description, String dateTime) {
        super(description, TaskType.DEADLINE);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (by: %s)", super.getTaskIcon(), super.toString(), this.dateTime);
    }
}
