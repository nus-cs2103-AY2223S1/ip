package duke.commands;

import java.time.LocalDateTime;

import duke.exceptions.DukeException;
import duke.gui.GuiText;
import duke.tools.SessionManager;
import duke.tools.TaskList;

/**
 * This class tells Duke to find all tasks that lie within the start and end date.
 */
public class WithinDateTimeCommand implements Command {

    /** The starting date and time to filter. */
    private LocalDateTime start;
    /** The ending date and time to filter. */
    private LocalDateTime end;

    /**
     * Constructs a WithinDateTimeCommand object.
     *
     * @param start The starting date and time to filter.
     * @param end The ending date and time to filter.
     */
    public WithinDateTimeCommand(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the between command from the user.
     *
     * @return The string to be shown by Duke on the dialogue box.
     */
    @Override
    public String execute() throws DukeException {
        TaskList taskList = SessionManager.getTaskList();
        return GuiText.formatWithinDateTime(taskList, start, end);
    }

    /**
     * Checks if the other object is equals to this one.
     *
     * @param o The other object to check on equality.
     * @return Boolean representing the equality of the object with this one.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof WithinDateTimeCommand) {
            WithinDateTimeCommand that = (WithinDateTimeCommand) o;
            if (start.isEqual(that.start) && end.isEqual(that.end)) {
                return true;
            }
        }
        return false;
    }
}
