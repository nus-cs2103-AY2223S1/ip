import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate dateTime;
    private DateTimeFormatter formatter;

    Event(String description, String dateTimeString) {
        super(description);
        try {
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            dateTime = LocalDate.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)",
                super.toString(), dateTime.format(formatter));
    }
}
