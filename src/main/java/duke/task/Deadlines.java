package duke.task;

import duke.util.DateAndTimeParser;

import java.time.LocalDate;

public class Deadlines extends Task {
    private String dateTime;
    private LocalDate date;

    public Deadlines(String description, String dateTime, LocalDate date) {
        super(description);
        this.dateTime = dateTime;
        this.date = date;
    }

    public String storedTaskString() {
        return "D|" + String.valueOf(this.isDone) + "|" + this.description + "|" + this.dateTime;
    }

    @Override
    public String toString() {
        if (date != null) {
            return "[D]" + super.toString() + " (by: " + DateAndTimeParser.convertDate(this.date) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + this.dateTime + ")";
        }
    }

}
