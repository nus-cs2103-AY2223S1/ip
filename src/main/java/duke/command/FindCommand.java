package duke.command;

import duke.Response;
import duke.Storage;
import duke.TaskList;

/**
 * Represents a command to list all current tasks.
 */
public class FindCommand extends Command{

    private String keyword;

    /**
     * Initialises the find command with the keyword to find.
     * @param keyword Keyword that matches task descriptions to be found.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes this command.
     * @param tasks Task list to find from.
     * @param storage Storage used in application.
     * @return The response of the execution.
     */
    @Override
    public Response execute(TaskList tasks, Storage storage) {
        String message = "Here are the matching tasks in your list:";
        return new Response(message + tasks.find(keyword).toString(), false);
    }
}
