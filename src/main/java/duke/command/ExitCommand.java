package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to exit the program
 */
public class ExitCommand extends Command {

    /**
     * to execute the {@code ExitCommand}
     *
     * @param tasks   the current {@code TaskList}
     * @param ui      the current {@code Ui}
     * @param storage the current {@code Storage}
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.update(tasks.getTasks());
        Command.end();
        return ui.exitTask();
    }
}
