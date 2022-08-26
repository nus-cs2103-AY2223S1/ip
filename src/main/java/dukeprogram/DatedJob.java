package dukeprogram;

import dukeprogram.parser.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatedJob extends Task {
    private String timeString;
    private LocalDateTime localDate;
    private final String prefix;

    public DatedJob(String name, String dateString, String prefix) {
        super(name);
        setDate(dateString);
        this.prefix = prefix;
    }

    public void setDate(String dateString) {
        this.localDate = DateTimeParser.parse(dateString);
        this.timeString = localDate == null ?
                dateString :
                localDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"));
    }

    public LocalDateTime getDate() {
        return localDate;
    }

    @Override
    public String toString() {
        String formality = localDate == null ? " [Informal Format]" : "";
        return super.toString()
                + String.format(" (%s: %s%s)", prefix, timeString, formality);
    }
}
