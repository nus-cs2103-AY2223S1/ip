package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;
import duke.exceptions.DukeException;

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
}
