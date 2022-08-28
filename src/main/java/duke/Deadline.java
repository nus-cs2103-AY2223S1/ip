package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {
    protected String by;
    protected LocalDate date;


    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }

    public void getDate() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate deadline = LocalDate.parse(this.by, formatter);
            this.date = deadline;
            this.by = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

