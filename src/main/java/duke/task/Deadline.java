package duke.task;

import duke.exception.EmptyDescException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    protected LocalDate d;

    public Deadline(String desc, String by) throws EmptyDescException {
        super(desc);
        this.by = by;
        if (desc.equals("") || by.equals("")) {
            throw new EmptyDescException("empty");
        }
        d = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + d.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toSave() {
        return "D | " + (isDone ? "1" : "0") + " |" + desc + " | " + by;
    }
}
