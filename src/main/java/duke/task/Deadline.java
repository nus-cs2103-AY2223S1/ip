package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    public static final String DEADLINE_REP = "D";
    protected LocalDate deadline;

    public Deadline(String content, String deadline) throws DukeException {
        super(content);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new DukeException("You need to input in yyyy-mm-dd format!");
        }
    }

    @Override
    public String toString() {
        return "[" + DEADLINE_REP + "]" + super.toString()
                + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String toStorage() {
        return DEADLINE_REP + super.toStorage() + Task.SEPARATOR + this.deadline;
    }
}
