package Dan;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

public class Event extends Task {
    private static final String ICON = "E";
    protected LocalDateTime dateString;

    Event(String description, String dateString) throws DateTimeParseException {
        super(description);
        this.dateString = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toDataString(String separator) {
        return String.format("%s%s%s%s", ICON, super.toDataString(separator), separator,
                this.dateString.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", ICON, super.toString(),
                this.dateString.format(DateTimeFormatter.ofPattern("MMM dd h:mma")));
    }
}
