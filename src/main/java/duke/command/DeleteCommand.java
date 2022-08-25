package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String deletedTaskDesc = taskList.getTaskN(index).toString();
        taskList.deleteTaskN(index);
        ui.printDeleteTaskReply(deletedTaskDesc, taskList.getNumOfTask());
        storage.overwriteFile(taskList.toStorageString());
    }
}
