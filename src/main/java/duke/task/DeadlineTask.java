package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import duke.util.DukeException;

/**
 * Task to be done by a deadline.
 * @author Jicson Toh
 */
public class DeadlineTask extends Task {
    protected LocalDate by;

    /**
     * Creates a new deadline task.
     * @param action input action.
     * @param isDone check if completed.
     * @param by deadline.
     * @throws DukeException exception if no deadline is given.
     */
    public DeadlineTask(String action, boolean isDone, String by) throws DukeException {
        super(action, isDone, by);
        if (Objects.equals(by, "")) {
            throw new DukeException();
        }
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public DeadlineTask(String action, String by) throws DukeException {
        this(action, false, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("E, MMM dd yyyy")) + ")";
    }
}
