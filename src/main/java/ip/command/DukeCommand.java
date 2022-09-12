package ip.command;

import ip.exception.BadDeadline;
import ip.exception.BadTimespan;
import ip.exception.IndexNotSpecified;
import ip.exception.MissingDescription;
import ip.exception.NoTaskFound;
import ip.utility.Storage;
import ip.utility.TaskList;

/**
 * Abstract class describing a command that may be executed.
 */
public abstract class DukeCommand {
    /**
     * Execute the DukeCommand.
     *
     * @param taskList Task list that the command acts on.
     * @param storage Storage that the command saves to/reads from.
     * @return Result message string.
     * @throws MissingDescription If the command is missing description.
     * @throws BadDeadline If the deadline specified is missing or not formatted correctly.
     * @throws BadTimespan If the time span is not specified.
     * @throws IndexNotSpecified If the index is not specified.
     * @throws NoTaskFound If there is no task found at the specified index.
     */
    public abstract String execute(TaskList taskList, Storage storage)
            throws MissingDescription,
            BadDeadline,
            BadTimespan,
            IndexNotSpecified,
            NoTaskFound;
}
