import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class Events extends Task {

    private LocalDateTime eventTime;

    Events(String description, String eventTime) {

        super(description, false);
        this.eventTime = LocalDateTime.parse(eventTime,
                DateTimeFormatter.ofPattern("[d/M/y HHmm]"));;
    }

    Events(String description, boolean isDone, String eventTime) {

        super(description, isDone);
        this.eventTime = LocalDateTime.parse(eventTime,
                DateTimeFormatter.ofPattern("[d/M/y HHmm]"));;

    }

    @Override
    public String toFileString() {

        return "E | " + (this.isDone ? 1 : 0) + " | " +
                this.description + " | " + this.eventTime;
    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + " (at: " +
                eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

}
