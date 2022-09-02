package ip.command;

import ip.exception.BadDeadline;
import ip.exception.BadTimespan;
import ip.exception.IndexNotSpecified;
import ip.exception.MissingDescription;
import ip.exception.NoTaskFound;
import ip.utility.TaskList;

/**
 * Abstract class describing a command that may be executed.
 */
public abstract class DukeCommand {
    public abstract String execute(TaskList taskList)
            throws MissingDescription,
            BadDeadline,
            BadTimespan,
            IndexNotSpecified,
            NoTaskFound;
}
