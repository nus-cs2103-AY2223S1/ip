package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;

/**
 * Representation of a command to mark a task as done.
 */
public class MarkStatusCommand extends Command {
    private int toggleTask;

    /**
     * Representation of a command to mark a task with a specified index.
     */
    public MarkStatusCommand(int toggleTask) {

        this.toggleTask = toggleTask;
    }

    /*
     * Mark task as done.
     * @throws DukeException if task cannot be found within the taskList
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        Task success = taskList.markStatus(this.toggleTask);
        storage.save(taskList);
        return ui.showToggleSuccess(success);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MarkStatusCommand) {
            MarkStatusCommand x = (MarkStatusCommand) obj;
            return this.toggleTask == x.toggleTask;
        }
        return false;
    }
}

