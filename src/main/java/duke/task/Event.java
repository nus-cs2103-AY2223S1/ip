package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {

    public Event(String description, LocalDateTime time) {
        super(description, Tag.Event, time);
    }
}
