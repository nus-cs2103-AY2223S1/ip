package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This is the deadline class that is
 * being called by the user
 */
public class Deadline extends Task{
    protected String by;
    protected LocalDate dateTime;

    /**
     * Constructor for class Deadline
     * @param description   Description of the deadline
     * @param by            The date of the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        assert description != "": "Description should not be empty";
        this.by = by;
        dateTime = LocalDate.parse(by, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
    }
    @Override
    public String toString() {

        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    @Override
    public String dTString() {
        return "[D]" + super.dTString() + " (by: " + this.dateTime.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)) + ")";
    }
}