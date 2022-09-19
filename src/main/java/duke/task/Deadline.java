package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {

    public Deadline(String description, LocalDateTime time) {
        super(description, Tag.Deadline, time);
    }
}
