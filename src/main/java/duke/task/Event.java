package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private String at;
    private String taskType;
    private LocalDate eventDate;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.taskType = "E";
        this.eventDate = LocalDate.parse(at);
    }

    public String getDescription() {
        return super.getDescription() + " | " + at;
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString(){
        return String.format("[E]" + super.toString() + " (at: %s)",
                eventDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
