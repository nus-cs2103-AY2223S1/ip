package duke.task;

import java.time.LocalDate;

/**
 * The duke.task.Deadline class extends the duke.task.Task class as it is a more specific type of task.
 */
public class Event extends Task {
    private LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    public String Stringify() {
        return String.format("%s | %s | %s", "E", super.stringify(), this.at);
    }

    @Override
    public String stringifyTask() {
        return String.format("%s | %s | %s", "E", super.stringifyTask(), this.at);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.at);
    }
}
