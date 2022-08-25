package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate at;

    public Event(String taskDescription, LocalDate at) {
        super(taskDescription);
        this.at = at;
    }

    public Event(String taskDescription, LocalDate at, Boolean isDone) {
        super(taskDescription);
        this.at = at;
        this.isDoneSetter(isDone);
    }


    @Override
    protected String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toStorageString() {
        return "E" + "|" + super.toStorageString() + "|" + at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
