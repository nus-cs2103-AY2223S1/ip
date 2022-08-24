import java.time.LocalDate;

public class Event extends Task {
    private final String occursAt;

    public Event(String taskName, String occursAt) {
        super(taskName);
        this.occursAt = occursAt;
    }

    @Override
    public String toString() {
        return "[E]" + (this.done ? "[X] " : "[ ] ") + this.taskName + " (at:" + this.occursAt + ")";
    }

}
