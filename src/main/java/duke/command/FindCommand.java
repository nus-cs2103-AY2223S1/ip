package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeNoMatchFoundException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * FindCommand is a Command that handles finding a task with a specific keyword.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */
public class FindCommand extends Command {
    private String keyword;
    /**
     * Constructor for FindCommand.
     *
     * @param keyword The keyword to be matched with a task.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Outputs a list of the tasks matching the specific keyword.
     *
     * @param tasks A TaskList containing the Tasks.
     * @param ui The Ui which handles interactions with the user.
     * @param storage The Storage which handles loading and saving data from the file.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> matchedTasks = tasks.findTasks(keyword);

        if (matchedTasks.size() < 1) {
            throw new DukeNoMatchFoundException(keyword);
        }

        return ui.displayMatch(matchedTasks);
    }
}
