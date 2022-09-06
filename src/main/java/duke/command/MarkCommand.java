package duke.command;

import duke.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

/**
 * Represents the command for marking a task in Duke's TaskList.
 */
public class MarkCommand implements Command{
    private final int TO_MARK;

    /**
     * Constructs a MarkCommand.
     *
     * @param TO_MARK Index of the task to be marked in Duke's TaskList.
     */
    public MarkCommand(int TO_MARK) {
        this.TO_MARK = TO_MARK;
    }

    /**
     * Executes the mark command by marking the task and printing the mark message.
     * Refreshes the storage to write the new changes.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has.
     * @param storage Storage created when starting Duke.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String res = tasks.mark(TO_MARK);
        storage.refresh(tasks);

        return res;
    }
}
