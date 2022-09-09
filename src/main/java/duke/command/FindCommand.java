package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a command to search for tasks containing the
 * specified search term.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private String keywords;

    /**
     * Constructs a Find Command with the search term the user
     * wants to search for.
     *
     * @param keywords Search term to be searched for.
     */
    public FindCommand(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.find(keywords);
    }
}
