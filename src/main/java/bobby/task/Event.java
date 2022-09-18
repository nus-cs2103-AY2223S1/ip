package bobby.task;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public Event(String description, Boolean isDone, LocalDateTime startTime, LocalDateTime endTime) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String dateTimePattern = "dd MMM yyyy, h.mm a";
        String timePattern = "h.mm a";
        LocalDate startDate = startTime.toLocalDate();
        LocalDate endDate = endTime.toLocalDate();
        if (startDate.equals(endDate)) {
            return String.format("[E] [%s] %s (at: %s to %s)",
                    this.getStatusIcon(),
                    this.description,
                    this.startTime.format(DateTimeFormatter.ofPattern(dateTimePattern)),
                    this.endTime.toLocalTime().format(DateTimeFormatter.ofPattern(timePattern)));
        }
        return String.format("[E] [%s] %s (at: %s to %s)",
                this.getStatusIcon(),
                this.description,
                this.startTime.format(DateTimeFormatter.ofPattern(dateTimePattern)),
                this.endTime.format(DateTimeFormatter.ofPattern(dateTimePattern)));
    }
    public String formatTaskString() {
        return String.format("E|%s|%s|%s|%s", this.description, this.isDone, this.startTime, this.endTime);
    }

}
