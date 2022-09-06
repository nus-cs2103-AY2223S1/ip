package duke.command;

import duke.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

/**
 * Represents the command for un-marking a task in Duke's TaskList.
 */
public class UnmarkCommand implements Command{

    private final int TO_UN_MARK;

    /**
     * Constructs an UnmarkCommand.
     *
     * @param TO_UN_MARK Index of the task to be unmarked in Duke's TaskList.
     */
    public UnmarkCommand(int TO_UN_MARK) {
        this.TO_UN_MARK = TO_UN_MARK;
    }

    /**
     * Executes the un-mark command by un-marking the task and printing the un-mark message.
     * Refreshes the storage to write the new changes.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has.
     * @param storage Storage created when starting Duke.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String res = tasks.unmark(TO_UN_MARK);
        storage.refresh(tasks);
        return res;
    }
}
