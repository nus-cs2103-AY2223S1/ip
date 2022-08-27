package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents a find command which given a search term,
 * finds a list Task objects matching the search term.
 *
 * @author njxue
 * @version v0.1
 */
public class FindCommand extends Command {
    /** Search term of the Task to find. */
    private String searchTerm;

    /**
     * Creates a new FileCommand.
     *
     * @param searchTerm Search term.
     */
    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Returns the format of the find command.
     *
     * @return The format of the find command.
     */
    public static String getFormat() {
        return "find <String>";
    }

    /**
     * Executes the find command. Finds a list of Task objects in the TaskList with descriptions that match the given
     * search term.
     *
     * @param tasks TaskList to find the Tasks from.
     * @param ui Ui object which interacts with the user.
     * @param storage Storage object which loads and saves tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList foundTasks = tasks.find(searchTerm);
        ListCommand listCommand = new ListCommand();
        listCommand.execute(foundTasks, ui, storage);
    }

    /**
     * Returns false, because find is not an application terminating command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
