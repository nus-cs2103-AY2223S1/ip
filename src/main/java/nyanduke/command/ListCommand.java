package nyanduke.command;

import nyanduke.Storage;
import nyanduke.Ui;
import nyanduke.task.TaskList;

/**
 * The ListCommand class represents a command
 * that will display NyanDuke's list of tasks.
 */
public class ListCommand extends Command {
    /**
     * Lists the tasks in NyanDuke.
     *
     * @param tasks The specified TaskList involved with the command.
     * @param ui The specified Ui involved with the command.
     * @param storage The specified Storage involved with the command.
     * @return A message containing the list of tasks in NyanDuke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }
}
