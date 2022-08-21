import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime dateTime;
    private DateTimeFormatter formatter;

    Event(String description, boolean isDone, String dateTimeString) {
        super(description, isDone);
        try {
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            dateTime = LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    public String SaveString() {
        return String.format("E | %s | %s | %s\n", super.isDone ? "1" : "0",
                super.description, dateTime.format(formatter));
    }
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)",
                super.toString(), dateTime.format(formatter));
    }
}
