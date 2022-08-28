public class Event extends Task {
    private String at;
    public Event(String input, boolean isDone) {
        super(isDone);
        String[] strArr = input.split("/at");
        this.description = strArr[0].trim();
        this.at = strArr[1].trim();
    }

    public Event(String input, String at, boolean isDone) {
        super(isDone);
        this.description = input;
        this.at = at;
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.Event;
    }

    @Override
    public String toDataForm() {
        String done = this.isDone ? "1" : "0";
        return "D|" + done + "|" + this.description + "|" + this.at + "\n";
    }

    @Override
    public String toString() {
        String head = "[E][" + this.getStatusIcon() + "] ";
        String body = this.description + " (at: " + this.at + ")";
        return head + body;
    }
}
