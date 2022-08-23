package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents a <code>find</code> command which given a search term,
 * finds a list <code>Task</code> objects matching the search term.
 */
public class FindCommand extends Command {
    /** Search term of the <code>Task</code> to find. */
    private String searchTerm;

    /**
     * Creates a new <code>FileCommand</code>.
     * 
     * @param searchTerm Search term.
     */
    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Returns the format of the <code>find</code> command.
     *
     * @return The format of the <code>find</code> command.
     */
    public static String getFormat() {
        return "find <String>";
    }

    /**
     * Executes the <code>find</code> command. 
     * Finds a list of <code>Task</code> objects in the <code>TaskList</code> with descriptions that match the 
     * given search term.
     *
     * @param tasks <code>TaskList</code> to find the <code>Tasks</code> from.
     * @param ui <code>Ui</code> object which interacts with the user.
     * @param storage <code>Storage</code> object which loads and saves tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList foundTasks = tasks.find(searchTerm);
        ListCommand listCommand = new ListCommand();
        listCommand.execute(foundTasks, ui, storage);
    }

    /**
     * Returns false, because <code>find</code> is not an application terminating command.
     * 
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
