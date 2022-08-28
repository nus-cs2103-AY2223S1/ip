package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * A class that encapsulates the find command.
 */
public class FindCommand extends Command {

    /** The query inputted by the user */
    private final String query;

    /**
     * The class constructor.
     *
     * @param query The query inputted by the user
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Handles the execution behaviour of the find command.
     *
     * @param tasks   The current list of tasks.
     * @param storage The storage of data.
     * @return The reply of the Duke bot.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        TaskList result = tasks.find(query);
        if (result.size() == 0) {
            return "No results match your search :(";
        } else {
            return "Here are the matching tasks in your list:\n" + result;
        }
    }

    /**
     * Returns the command type.
     *
     * @return The command type.
     */
    @Override
    public String getCommand() {
        return "find";
    }

    /**
     * Returns the string representation of the command.
     *
     * @return The string representation of the command.
     */
    @Override
    public String toString() {
        return "find " + this.query;
    }
}
