package duke.command;

import duke.DukeException;
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
     * @throws DukeException when the command cannot be successfully executed.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }
}
