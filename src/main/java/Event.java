public class Event extends Task {
    private String at;
    public Event(String input) {
        String[] strArr = input.split("/at");
        this.description = strArr[0].trim();
        this.at = strArr[1].trim();
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.Event;
    }

    @Override
    public String toString() {
        String head = "[E][" + this.getStatusIcon() + "] ";
        String body = this.description + " (at: " + this.at + ")";
        return head + body;
    }
}
