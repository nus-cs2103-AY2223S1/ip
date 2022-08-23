import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Event extends Task{
    String preposition;
    LocalDateTime startDateTime, endDateTime;
    LocalTime endTime;

    public Event(String description, String preposition, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.preposition = preposition;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Event(String description, String preposition, LocalDateTime startDateTime, LocalTime endTime) {
        super(description);
        this.preposition = preposition;
        this.startDateTime = startDateTime;
        this.endTime = endTime;
    }

    @Override
    public boolean happensOn(LocalDate searchDate) {
        return startDateTime.toLocalDate().equals(searchDate);
    }

    @Override
    public String fileDescription() {
        if (endTime == null) {
            return "E | " + super.fileDescription() + " | " + preposition
                    + " | " + startDateTime + " | " + endDateTime;
        } else {
            return "E | " + super.fileDescription() + " | " + preposition
                    + " | " + startDateTime + " | " + endTime;
        }
    }

    @Override
    public String toString() {
        if (endTime == null) {
            return "[E]" + super.toString() + " (" + preposition + ": "
                    + startDateTime.format(super.dateTimeFormatter) + " to "
                    + endDateTime.format(super.dateTimeFormatter) + ")";
        } else {
            return "[E]" + super.toString() + " (" + preposition + ": "
                    + startDateTime.format(super.dateTimeFormatter) + " to "
                    + endTime.format(super.timeFormatter) + ")";
        }
    }
}
