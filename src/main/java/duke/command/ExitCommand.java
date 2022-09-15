package duke.command;

import duke.TaskList;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * {@inheritDoc}
     */
    public String execute(TaskList tasks) {
        return "Bye. Hope to see you again soon!\n";
    }
}
