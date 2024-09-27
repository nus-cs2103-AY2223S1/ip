package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Encapsulates the command of exiting the programme.
 *
 * @author Sun Ruoxin
 */
public class ExitCommand extends Command {
    /**
     * Executes the command.
     * Show the feedback to the user.
     *
     * @param tasks the list of tasks
     * @param storage the storage
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "exit";
    }
}
