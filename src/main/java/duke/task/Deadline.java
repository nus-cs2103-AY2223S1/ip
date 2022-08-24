package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public String toFileDescription() {
        return "D" + " | " + super.toFileDescription() + " | " + this.by;
    }

    public static Deadline fromFileDescription(String input) {
        String[] strArray = input.split(" \\| ", 4);
        String description = strArray[2];
        String by = strArray[3];
        LocalDate dateBy = LocalDate.parse(by);
        Deadline deadline = new Deadline(description, dateBy);
        if (strArray[1].equals("1")) {
            deadline.markDone();
        }
        return deadline;
    }
    public boolean isHappeningOnDate(LocalDate localDate) {
        return this.by.equals(localDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMMM d yyyy")) + ")";
    }
}
