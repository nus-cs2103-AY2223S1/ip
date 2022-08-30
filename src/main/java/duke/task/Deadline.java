package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime time;

    public Deadline(String description, LocalDateTime time) {
        super(description, Tag.Deadline);
        this.time = time;
    }

    @Override
    public String getDescription() {
        return description + " (" + DateTimeFormatter.ofPattern("MMM dd yyyy H:mm").format(time) + ")";
    }
}
