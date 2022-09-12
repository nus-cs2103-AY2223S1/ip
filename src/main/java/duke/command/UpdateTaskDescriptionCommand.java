package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Storage;
import duke.UI;

public class UpdateTaskDescriptionCommand extends Command {
    private int taskNo;
    private String updatedField;

    public UpdateTaskDescriptionCommand(int taskNo, String updatedField) {
        this.taskNo = taskNo;
        this.updatedField = updatedField;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.updateTaskDescription(this.taskNo, this.updatedField);
        UI.updateTaskDesc(taskList.getTask(this.taskNo));
        response = UI.updateTaskDescResponse(taskList.getTask(this.taskNo));
        storage.saveData(taskList);
    }
}
