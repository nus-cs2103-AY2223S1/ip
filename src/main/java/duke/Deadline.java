package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//@@author chengda300
//Reused from https://nus-cs2103-ay2223s1.github.io/website/schedule/week2/project.html
// with minor modifications
public class Deadline extends Task {
    private String by;
    private LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDate by) {
        super(description);
        this.date = by;
    }

    @Override
    public String toString() {
        String deadline = this.by == null
                ? this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : this.by;
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
//@@author