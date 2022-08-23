package tako.command;

import java.io.IOException;

import tako.Storage;
import tako.TaskList;
import tako.Ui;

/**
 * Command to list tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Lists all tasks in the task list.
     *
     * @param tasks Task list.
     * @param ui Ui.
     * @param storage Task storage.
     * @throws IOException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showList(tasks);
    }

    /**
     * Returns false as Tako cannot exit after this command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
