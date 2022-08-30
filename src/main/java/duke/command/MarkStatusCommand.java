package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;
import duke.exceptions.DukeException;

public class MarkStatusCommand extends Command {
    private int toggleTask;

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
}

