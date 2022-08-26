package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by = "";
    protected LocalDate byDate;
    protected LocalTime byTime;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        dateTimeConverter(by);
    }

    public Deadline(String description, LocalDate byDate, LocalTime byTime) {
        super(description);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    private void dateTimeConverter(String by) {
        if (by.length() > 10) {
            int spacePos = by.indexOf(" ");
            String date = by.substring(0, spacePos);
            String time = by.substring(spacePos + 1);
            this.byDate = LocalDate.parse(date);
            this.byTime = LocalTime.parse(time);
        } else {
            this.byDate = LocalDate.parse(by);
        }
    }



    @Override
    public String toString() {
        return ("D | "
                + super.toString()
                + " | "
                + this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ((this.byTime == null)
                    ? ""
                    : " | " + this.byTime.toString()));
    }
}
