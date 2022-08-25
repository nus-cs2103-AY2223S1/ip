package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends duke.task.Task {
    LocalDateTime by;

    public Deadline(String title, LocalDateTime by) {
        super(title);
        this.by = by;
    }

    public Deadline(String title, LocalDateTime by, Boolean isDone) {
        super(title, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        // can abstract these constants out into a constants file
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

        return "[D] " + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    public String toSaveString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return "D|" + super.toSaveString() + "|" + by.format(formatter);
    }
}
