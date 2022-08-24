package duke;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = LocalDate.parse(duration);
    }

    public Event(String description, boolean isDone , String duration) {
        super(description, isDone);
        this.duration = LocalDate.parse(duration);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " ( at: " + duration + " )";
    }

    @Override
    public String toStorageFormat() {
        return "E | " + super.toStorageFormat() + " | " + duration;
    }

}
