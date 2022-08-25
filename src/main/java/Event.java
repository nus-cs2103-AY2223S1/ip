
public class Event extends Task {
    final String occursAt;

    public Event(String taskName, String occursAt, boolean isDone) {
        super(taskName, isDone);
        this.occursAt = occursAt;
    }

    @Override
    public String taskToFileString() {
        return " E " + "| " + (this.done ? "1 " : "0 ") + "|" + this.taskName + "|" + this.occursAt;
    }

    @Override
    public String toString() {
        return "[E]" + (this.done ? "[X]" : "[ ]") + this.taskName + "(at:" + this.occursAt + ")";
    }

}
