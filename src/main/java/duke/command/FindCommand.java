package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * FindCommand is a Command that finds Task(s) with a specified keyword.
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public class FindCommand extends Command {
    private String keyword;

    /**
     * A constructor for FindCommand.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * A method that displays the list of Task(s) with the specified keyword.
     *
     * @param tasks The TaskList containing the task list.
     * @param ui The Ui dealing with interactions with the user.
     * @param storage The Storage dealing with loading tasks from the file and saving tasks in the file.
     * @return The list of Task(s) containing the specified keyword.
     * @throws DukeException If there are no matching Task(s) found with the specified keyword.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> foundTasks = tasks.findTask(keyword);
        if (foundTasks.isEmpty()) {
            throw new DukeException("No matching tasks found!");
        } else {
            return ui.showFind(foundTasks);
        }
    }
}
