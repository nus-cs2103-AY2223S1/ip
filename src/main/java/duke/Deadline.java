package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class Deadline extends Task {
    protected LocalDate by;
    private static final String TYPE = "[D]";


    public Deadline(String description, String notFormattedDate) {
        super(description);
        by = LocalDate.parse(notFormattedDate);
    }

    //Abstract out formatter to another function
    public String customFormatter(LocalDate ld) {
        return ld.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {

        return TYPE + super.toString() + "(by: " + customFormatter(by) + ")";
    }
}