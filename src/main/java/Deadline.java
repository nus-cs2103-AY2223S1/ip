import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime dateTime;

    public Deadline(String description, String by) {
        super(description);
        String[] dateTimeSplit = by.split(" ");
        String isoDateFormat = "";
        if (dateTimeSplit.length == 1) {
            isoDateFormat = String.format("%sT23:59",dateTimeSplit[0]);
        } else {
            isoDateFormat = String.format("%sT%s", dateTimeSplit[0], dateTimeSplit[1]);
        }
        this.dateTime= LocalDateTime.parse(isoDateFormat);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        String formattedDateTime = this.dateTime.format(formatter);
        return String.format("[E]%s (by: %s)", super.toString(), formattedDateTime);
    }
}