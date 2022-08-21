import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate dateTime;
    private DateTimeFormatter formatter;

<<<<<<< .merge_file_a21232
    Event(String description, String dateTimeString) {
        super(description);
        try {
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            dateTime = LocalDate.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw e;
        }
=======
    Event(String description, boolean isDone, String details) {
        super(description, isDone);
        this.details = details;
>>>>>>> .merge_file_a20028
    }

    public String SaveString() {
        return String.format("E | %s | %s | %s\n", super.isDone ? "1" : "0",
                super.description, details);
    }
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)",
                super.toString(), dateTime.format(formatter));
    }
}
