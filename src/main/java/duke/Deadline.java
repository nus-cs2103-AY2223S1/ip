package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    public Deadline(String description,String by) {
        super(description);
        this.by = by;
    }



    @Override
    public String toString() throws DateTimeException {
        try {


            LocalDate d1 = LocalDate.parse(by.substring(1), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String deadline = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return "[D]" + super.toString() + " (by: " + deadline + ")";

        } catch (DateTimeParseException e) {
            return "[D]" + super.toString() + by ;
        }
    }



}