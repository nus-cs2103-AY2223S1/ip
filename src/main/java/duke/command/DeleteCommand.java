package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;

public class DeleteCommand extends Command {

    private int deleteTask;

    /*
     * Command that represents the removal of task from Storage.
     */
    public DeleteCommand(int deleteTask) {
        this.deleteTask = deleteTask;
    }

    /*
     * Attempts to remove task specified from the taskList.
     * If successful, Ui shows success on CLI.
     * @throws DukeException when task specified is in improper format
     *         or task cannot be found.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        Task success = taskList.deleteTask(this.deleteTask);
        storage.save(taskList);
        ui.showDeleteSuccess(success, taskList.numOfTask());
    }
}
