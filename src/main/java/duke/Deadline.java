package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate date;

    public Deadline(String item, String dateString) {
        super(item);
        this.date = LocalDate.parse(dateString);
    }

    private String dateFormat(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + " by: " + dateFormat(date);
    }
}
