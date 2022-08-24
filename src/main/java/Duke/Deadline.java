package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    protected LocalDate dateTime;

    public Deadline(String description, String by) {
        super(description);
        dateTime = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
//        System.out.println(dateTime.toString());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.dateTime.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)) + ")";
    }
}
