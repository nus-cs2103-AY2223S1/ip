package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime eventAt;

    public Event(String description, LocalDateTime eventAt) {

        super(description, false);
        this.eventAt = eventAt;
    }

    public Event(String description, boolean isDone, String eventAt) {

        super(description, isDone);
        this.eventAt = LocalDateTime.parse(eventAt,
                DateTimeFormatter.ofPattern("d/M/y HHmm"));;

    }

    @Override
    public String fileString() {

        return "E" + super.fileString() + " | " +
        eventAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + " (at: " +
                eventAt.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";

    }

}
