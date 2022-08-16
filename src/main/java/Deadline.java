public class Deadline extends Task {
    private String by;

    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        return super.toString() + "(by:" + by + ")";
    }
}
