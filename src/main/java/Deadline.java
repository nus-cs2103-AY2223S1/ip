import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {

public class Deadline extends Task {

    private LocalDateTime dateTime;

    public Deadline(String description, String dateTime) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        this.dateTime =  LocalDateTime.parse(dateTime,formatter);
    }

    public String getDate() {
        return this.dateTime.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL));
    }

    public String getDate() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)) + ")";
    }
}
}