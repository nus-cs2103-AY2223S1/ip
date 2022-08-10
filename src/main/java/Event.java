import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {

    private static final String TYPE = "[E]";
    protected LocalDate by;

    public Event(String description, String unformattedDate) {
        super(description);
        by = LocalDate.parse(unformattedDate); //TODO: figure out how to parse multiple types
    }


    public String CustomFormatter(LocalDate ld) {
        return ld.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {

        return TYPE + super.toString() + "(at: " + CustomFormatter(by) + ")";
    }
}