package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {

    private String oldDate;
    private LocalDateTime dateTime;
    private String formattedDateTime;

    public Deadline(String description, String dateTime) {
        super(description);
        this.oldDate = dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        this.dateTime = LocalDateTime.parse(dateTime, formatter);
        formattedDateTime = this.dateTime.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)) + " " + this.dateTime.format(DateTimeFormatter
                .ofLocalizedTime(FormatStyle.SHORT));
    }

    public String getDate() {
        return formattedDateTime;
    }

    public String getOldDate() {
        return this.oldDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedDateTime + ")";
    }
}