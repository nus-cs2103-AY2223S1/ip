package duke.task;

import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at, Task.inputFormat);
    }

    @Override
    public String toStorage() {
        return "E | " + super.toStorage() + " | " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(Task.outputFormat) + ")";
    }
}
