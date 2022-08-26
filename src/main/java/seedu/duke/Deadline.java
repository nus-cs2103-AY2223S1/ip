package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate dueDate;
    protected String dueTime;

    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
        this.dueTime = "";
    }

    public Deadline(String description, LocalDate dueDate, String dueTime) {
        super(description);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    public String dueDateToString() {
        String dueDateString = this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        if (this.dueTime.equals("")) {
            String output = String.format("%s",dueDateString);
            return output;
        } else {
            String output = String.format("%s, %s",dueDateString, this.dueTime);
            return output;
        }
    }

    @Override
    public String toString() {
        String output = String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.description, this.dueDateToString());
        return output;
    }
}
