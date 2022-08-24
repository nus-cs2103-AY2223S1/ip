package TaskList;

import TaskList.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for task.
     *
     * @param detail String
     */
    public Deadline(String detail, String by) {
        super(detail);
        this.by = LocalDate.parse(by);
    }
    public Deadline(String detail, boolean isDone, String by) {
        super(detail, isDone);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()  + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String storedData() {
        return "D" + "|" + super.storedData() + "|" + by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
