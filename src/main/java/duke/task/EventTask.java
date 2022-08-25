package duke.task;

import duke.util.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Event task to be done at a specific date.
 */
public class EventTask extends Task {
    protected LocalDate event;

    /**
     * Creates event task object.
     * @param action user input action to be done.
     * @param isDone check is completed.
     * @param event event to be completed at.
     * @throws DukeException if event is not specified.
     */
    public EventTask(String action, boolean isDone, String event) throws DukeException {
        super(action, isDone, event);
        if (Objects.equals(event, "")) {
            throw new DukeException();
        }
        this.event = LocalDate.parse(event, DateTimeFormatter.ofPattern("dd/MM/yyyy"));;
    }

    public EventTask(String action, String event) throws DukeException {
        this(action, false, event);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + event.format(DateTimeFormatter.ofPattern("E, MMM dd yyyy")) + ")";
    }
}
