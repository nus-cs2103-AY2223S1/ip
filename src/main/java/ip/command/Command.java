package ip.command;

import ip.TaskList;
import ip.exception.*;

public abstract class Command {
    public abstract void execute(TaskList taskList)
            throws MissingDescription,
            BadDeadline,
            BadTimespan,
            IndexNotSpecified,
            NoTaskFound;
}
