package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The ListCommand class represents user command list.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks.getTaskList());
    }
}
