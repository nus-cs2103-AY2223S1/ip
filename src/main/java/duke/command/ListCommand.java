package duke.command;

import duke.manager.Storage;
import duke.manager.TaskList;

/**
 * Represents a command to display the list of current tasks.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String response = tasks.toString();

        return response;
    }
}