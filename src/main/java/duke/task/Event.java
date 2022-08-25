package duke.task;

public class Event extends Task {
    private String eventTimePeriod;

    public Event(String taskDescription, String eventTimePeriod) {
        super(taskDescription);
        this.eventTimePeriod = eventTimePeriod;
    }

    public Event(String taskDescription, boolean isTaskDone, String eventTimePeriod) {
        super(taskDescription, isTaskDone);
        this.eventTimePeriod = eventTimePeriod;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventTimePeriod + ")";
    }

    public String toFileString() {
        return "E | " + super.toFileString() + " | " + this.eventTimePeriod + "\n";
    }
}
