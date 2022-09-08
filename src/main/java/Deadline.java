package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with task description and deadline date.
 */
class Deadline extends Task {

    private LocalDate time;
    private String timeInString;

    Deadline(String description, String Date) {
        super(description);
        this.time = LocalDate.parse(Date);
        this.timeInString = Date;
    }

    @Override
    public String toString() {
        String timeString = this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " by " + timeString;
    }

    public String write() {
        return "D" + super.write() + ":" + this.timeInString;
    }

}