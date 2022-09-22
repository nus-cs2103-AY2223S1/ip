package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * A class that encapsulates the list command.
 */
public class ListCommand extends Command {

    /**
     * Handles the execution behaviour of the list command.
     *
     * @param tasks   The current list of tasks.
     * @param storage The storage of data.
     * @return The reply of the Duke bot.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.toString();
    }

    /**
     * Returns the command type.
     *
     * @return The command type.
     */
    @Override
    public String getCommand() {
        return "list";
    }

    /**
     * Returns the string representation of the command.
     *
     * @return The string representation of the command.
     */
    @Override
    public String toString() {
        return "list";
    }
}
