package duke.command;

import duke.TaskList;
import duke.models.Task;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Marks the Task in the TaskList
 */
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        Task t = tasks.getTask(index);
        t.markAsDone();
        return ui.showTaskMarkMessage(t);
    }
}
