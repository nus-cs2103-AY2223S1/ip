package duke.task;

import duke.task.Task;

import java.time.LocalDate;

public class Event extends Task {
    public static String DELIMITER = " /at ";

    private LocalDate time;

    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    public Event(String description, LocalDate time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }

    @Override
    public String toSave() {
        return "E" + Task.SAVE_SEPARATOR + this.getIsDoneString() + Task.SAVE_SEPARATOR + this.getDescription()
                + Task.SAVE_SEPARATOR + this.time;
    }
}
