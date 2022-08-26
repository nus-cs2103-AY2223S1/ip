package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String at = "";
    protected LocalDate atDate;
    protected LocalTime atTime;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        convertToDateTime(at);
    }

    public Event(String description, LocalDate atDate, LocalTime atTime) {
        super(description);
        this.atDate = atDate;
        this.atTime = atTime;
    }

    private void convertToDateTime(String at) {
        if (at.length() > 10) {
            int spacePos = at.indexOf(" ");
            String date = at.substring(0, spacePos);
            String time = at.substring(spacePos + 1);
            this.atDate = LocalDate.parse(date);
            this.atTime = LocalTime.parse(time);
        } else {
            this.atDate = LocalDate.parse(at);
        }
    }

    @Override
    public String toString() {
        return ("E | "
                + super.toString()
                + " | "
                + this.atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ((this.atTime == null)
                        ? ""
                        : " | " + this.atTime.toString()));
    }
}
