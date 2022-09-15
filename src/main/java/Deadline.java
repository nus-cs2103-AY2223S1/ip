package anya;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with task description and deadline date.
 */
class Deadline extends Task {

    private LocalDate time;
    private String timeInString;

    Deadline(String description, String date) {
        super(description);
        this.time = LocalDate.parse(date);
        this.timeInString = date;
    }

    protected LocalDate getTime() {
        return this.time;
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
