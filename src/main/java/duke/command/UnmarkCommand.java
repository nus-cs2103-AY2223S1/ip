package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * UnmarkCommand unmakrs a task as done.
 */
public class UnmarkCommand extends Command {
    private int indexToUnmark;

    /**
     * Constructor for UnmarkCommand
     * @param indexToUnmark index of task to unmark.
     */
    public UnmarkCommand(int indexToUnmark) {
        super();
        this.indexToUnmark = indexToUnmark;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.getTask(indexToUnmark).markAsUndone();
        ui.showUnmarkMessage(taskList.getTask(indexToUnmark));
    }
}
