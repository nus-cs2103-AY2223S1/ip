public class Event extends Task {
    private String at;

    public Event(String taskDescription, String at) {
        super(taskDescription);
        this.at = at;
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.toString() + "(at:" + at + ")";
    }
}
