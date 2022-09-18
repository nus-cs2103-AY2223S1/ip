import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class Event extends Task {

    private LocalDateTime eventTime;

    Event(String description, String eventTime) {

        super(description, false);
        this.eventTime = LocalDateTime.parse(eventTime,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    Event(String description, boolean isDone, String eventTime) {

        super(description, isDone);
        this.eventTime = LocalDateTime.parse(eventTime,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));

    }

    @Override
    public String toFileString() {

        return "E" + super.toFileString();
    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + " (at: " +
                eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";

    }

}
