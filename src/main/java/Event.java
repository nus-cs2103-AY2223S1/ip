import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(at, formatter);
            this.at = localDate;
        } catch (DateTimeParseException e) {
            throw new DukeException("The date included should follow this format: " +
                    "dd/MM/yyyy");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String date = this.at.format(formatter);
        return "[E]" + super.toString() + String.format(" (at: %s)", date);
    }

    @Override
    public LocalDate getDate() {
        return this.at;
    }

    @Override
    public String getTaskType() {
        return "E";
    }
}
