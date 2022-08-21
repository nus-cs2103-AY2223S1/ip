import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate date;
    private DateTimeFormatter formatter;

    Deadline(String description, String dateString) {
        super(description);
        try {
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.date = LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(), date.format(formatter));
    }
}
