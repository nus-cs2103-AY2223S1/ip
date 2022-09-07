package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is an extension of Task.
 */
public class Event extends Task {
    private String at;
    private String taskType;
    private LocalDate eventDate;

    /**
     * Constructor for Event.
     * @param description Event description.
     * @param at when the event is happening.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.taskType = "E";
        this.eventDate = LocalDate.parse(at);
    }

    /**
     * Constructor for Event.
     * @param description Event description.
     * @param at when the event is happening.
     * @param priority priority of task.
     */
    public Event(String description, String at, String priority) {
        super(description, priority);
        this.at = at;
        this.taskType = "E";
        this.eventDate = LocalDate.parse(at);
    }

    /**
     * Returns event description.
     * @return even description.
     */
    public String getDescription() {
        return super.getDescription() + " | " + at;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return String.format("[E]" + super.toString() + " (at: %s)",
                eventDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
