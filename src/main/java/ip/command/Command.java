package ip.command;

import ip.TaskList;
import ip.exception.BadDeadline;
import ip.exception.BadTimespan;
import ip.exception.IndexNotSpecified;
import ip.exception.MissingDescription;
import ip.exception.NoTaskFound;

/**
 * Abstract class describing a command that may be executed.
 */
public abstract class Command {
    public abstract void execute(TaskList taskList)
            throws MissingDescription,
            BadDeadline,
            BadTimespan,
            IndexNotSpecified,
            NoTaskFound;
}
