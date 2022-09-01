package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents the command for marking a task in Duke's TaskList.
 */
public class MarkCommand implements Command{
    private final int toMark;

    /**
     * Constructs a MarkCommand.
     *
     * @param toMark Index of the task to be marked in Duke's TaskList.
     */
    public MarkCommand(int toMark) {
        this.toMark = toMark;
    }

    /**
     * Executes the mark command by marking the task and printing the mark message.
     * Refreshes the storage to write the new changes.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has.
     * @param ui Ui created when starting Duke.
     * @param storage Storage created when starting Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.dukePrint(tasks.mark(toMark));
        storage.refresh(tasks);
    }

    /**
     * Returns whether this command is an exit command.
     *
     * @return False since this is not an exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
