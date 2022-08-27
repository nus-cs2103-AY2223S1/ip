package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Represents the command for unmarking a task in Duke's TaskList.
 */
public class UnmarkCommand implements Command{

    private final int toUnmark;

    /**
     * Constructs an UnmarkCommand
     *
     * @param toUnmark the index of the task to be unmarked in Duke's TaskList.
     */
    public UnmarkCommand(int toUnmark) {
        this.toUnmark = toUnmark;
    }

    /**
     * Executes the unmark command by unmarking the task and printing the unmark message.
     * Refreshes the storage to write the new changes.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has
     * @param ui The Ui created when starting Duke
     * @param storage The Storage created when starting Duke
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.dukePrint(tasks.unmark(toUnmark));
        storage.refresh(tasks);
    }

    /**
     * Returns whether this command is an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
