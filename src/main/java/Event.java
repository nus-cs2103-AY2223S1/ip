import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    protected String timeQualifier;
    protected LocalDate timeDescription;


    Event(String description, String timeQualifier, String timeDescription) {
        super(description);
        this.timeQualifier = timeQualifier;
        this.timeDescription = LocalDate.parse(timeDescription);
    }

    @Override
    public String getTaskTypeIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return "[" + getTaskTypeIcon() + "]" + super.toString() + " (" + timeQualifier + ": " + timeDescription.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
