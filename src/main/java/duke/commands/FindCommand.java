package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to search for tasks containing the
 * specified search term.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private String keyword;

    /**
     * Constructs a Find Command with the search term the user
     * wants to search for.
     *
     * @param keyword Search term to be searched for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.find(keyword);
    }
}
