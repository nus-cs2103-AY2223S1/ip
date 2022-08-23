package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Deadline extends Task {
    public static final String TASK_WORD = "deadline";
    private final LocalDateTime by;

    public Deadline(String description, boolean done, LocalDateTime by) {
        super(description, done);
        this.by = by;
    }

    @Override
    public Optional<LocalDateTime> getTime() {
        return Optional.of(this.by);
    }

    @Override
    public String getTaskType() {
        return "duke.tasks.Deadline";
    }

    @Override
    public String toString() {
        String checkbox = this.getDone() ? "[D][X]" : "[D][ ]";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        String dateFormatted = "(by: " + this.by.format(formatter) + ")";
        return checkbox + " " + super.getDescription() + " " + dateFormatted;
    }
}
