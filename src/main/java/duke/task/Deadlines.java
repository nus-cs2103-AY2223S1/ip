package duke.task;

import duke.util.DateAndTimeFormatter;

import java.time.LocalDate;

public class Deadlines extends Task {
    private String dateTime;
    private LocalDate date;

    public Deadlines(String description, String dateTime, LocalDate date) {
        super(description);
        this.dateTime = dateTime;
        this.date = date;
    }

    @Override
    public String savedTaskString() {
        return String.format("D|%d|%s|%s", this.isDone ? 1 : 0, this.description, this.dateTime);
    }
    @Override
    public String toString() {
        if (date == null) {
            return "[D]" + super.toString() + " (by: " + dateTime + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + DateAndTimeFormatter.convertDate(this.date) + ")";
        }

    }
}
