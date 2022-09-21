package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

public class Deadline extends duke.task.Task {
    private LocalDateTime by;

    public Deadline(String title, LocalDateTime by) throws DukeException {
        super(title);
        this.by = by;
    }

    public Deadline(String title, LocalDateTime by, Boolean isDone) {
        super(title, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_OUTPUT_FORMAT);
        return "[D] " + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    public String toSaveString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_INPUT_FORMAT);
        return "D|" + super.toSaveString() + "|" + by.format(formatter);
    }
}
