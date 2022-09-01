package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        String deletedTaskDesc = taskList.getTaskN(index).toString();
        taskList.deleteTaskN(index);
        storage.overwriteFile(taskList.toStorageString());
        return Command.wrapper.getDeleteResponse(deletedTaskDesc, taskList.getNumOfTask());
    }
}
