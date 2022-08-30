package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents exit command.
 */
public class ExitCommand extends Command {

    /**
     * Execute exit command.
     *
     * @param tasks List of task.
     * @param ui User interface of programme.
     * @param storage Storage of programme.
     * @throws DukeException if error in saving file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.exit();
        storage.save(tasks.toString());
    }

    /**
     * Returns exit status of programme.
     *
     * @return exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
