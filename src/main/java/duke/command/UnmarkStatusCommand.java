package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;

/**
 * Representation of a command to unmark a task as done.
 */
public class UnmarkStatusCommand extends Command {
    private int toggleTask;

    public UnmarkStatusCommand(int toggleTask) {
        this.toggleTask = toggleTask;
    }

    /*
     * Unmark a task as done.
     * @throws DukeException when task unable to be found in taskList
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        Task success = taskList.unmarkStatus(this.toggleTask);
        storage.save(taskList);
        return ui.showToggleSuccess(success);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UnmarkStatusCommand) {
            UnmarkStatusCommand x = (UnmarkStatusCommand) obj;
            return this.toggleTask == x.toggleTask;
        }
        return false;
    }
}
