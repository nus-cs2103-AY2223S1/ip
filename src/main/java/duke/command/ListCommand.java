package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The ListCommand class represents a Command that lists all the Tasks
 * currently the in the TaskList.
 *
 * @author Edric Yeo
 */
public class ListCommand extends Command {

    /**
     * Returns a message including all the Tasks currently in the TaskList.
     *
     * @param tasks   The TaskList instance for the task manager.
     * @param ui      The Ui instance for the task manager.
     * @param storage The Storage instance for the task manager.
     * @return A message that displays all the current tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTasks(tasks, "Here are the tasks in your list:");
    }

    /**
     * Returns the String representation of an ListCommand.
     *
     * @return String representation of an ListCommand.
     */
    @Override
    public String toString() {
        return "List command";
    }
}
