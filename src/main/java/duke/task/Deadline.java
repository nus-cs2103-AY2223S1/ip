package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate date;

    public Deadline(String taskDescription, LocalDate date) {
        super(taskDescription);
        this.date = date;
    }

    public Deadline(String taskDescription, boolean done, LocalDate date) {
        super(taskDescription, done);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.
                ofPattern("MMM dd yyyy")) + ")";}
}
