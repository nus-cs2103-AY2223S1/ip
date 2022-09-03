package Duke.Tasks;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime eventTime;


    public Event (String description, LocalDate eventDate, LocalTime eventTime, boolean isDone) {
        super(description, isDone);
        this.eventTime = LocalDateTime.of(eventDate, eventTime);
    }
    public Event (String description, LocalDateTime eventTime, boolean isDone) {
        super(description, isDone);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (%s)", super.toString(),
                this.eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }

    @Override
    public String save() {
        return String.format("E | %b | %s | %s\n",
                super.getIsDone(),
                this.discription,
                this.eventTime.toString());
    }

    @Override
    public String getTaskType() { return "Event"; }

    @Override
    public LocalDateTime getDateTime() { return this.eventTime; }
}
