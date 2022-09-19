import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class Event extends Task {

    private LocalDateTime eventTime;

    Event(String description, LocalDateTime eventTime) {

        super(description, false);
        this.eventTime = eventTime;
    }

    Event(String description, boolean isDone, LocalDateTime eventTime) {

        super(description, isDone);
        this.eventTime = eventTime;

    }

    @Override
    public String fileString() {

        return "E" + super.fileString();
    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + " (at: " +
                eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";

    }

}
