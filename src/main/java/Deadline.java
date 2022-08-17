public class Deadline extends Task {
    private String dateTime;

    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public String getTaskIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (by: %s)", this.getTaskIcon(), super.toString(), this.dateTime);
    }
}
