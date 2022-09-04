package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The ListCommand class represents a command
 * that will display Duke's list of tasks.
 */
public class ListCommand extends Command {
    /**
     * Displays the list of tasks in Duke.
     *
     * @param tasks The specified TaskList involved with the command.
     * @param ui The specified Ui involved with the command.
     * @param storage The specified Storage involved with the command.
     * @return A message containing the list of tasks in Duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }
}
