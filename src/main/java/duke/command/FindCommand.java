package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;


/**
 * Command to find tasks using a keyword
 *
 * @author benjytan45678
 * @version 0.1
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a command that finds tasks using a keyword.
     *
     * @param keyword finds specified tasks that matches the keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds the tasks from the task list and stored the updated task list in the local file
     *
     * @param tasks specified list of tasks
     * @param ui specific ui object to interact with
     * @param storage specific storage to store the updated task list
     * @throws DukeException specific error message to be thrown
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> filteredTaskList = tasks.find(this.keyword);
        String message = ui.showFind(filteredTaskList);
        return message;
    }
}
