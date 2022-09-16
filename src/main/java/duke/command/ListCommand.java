package duke.command;

import duke.TaskList;

/**
 * Represents a command to show the entire task list.
 */
public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     */
    public String execute(TaskList tasks) {
        return tasks.toString();
    }
}
