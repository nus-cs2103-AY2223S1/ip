package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Task;

public class DeleteCommand extends Command {

    String taskNumber;

    public DeleteCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String exec(TaskList taskList, Storage storage , Ui ui) throws DukeException {
        Task deletedTask = taskList.deleteTask(this.taskNumber);
        return ui.showDeleteMessage(deletedTask, taskList.getTaskListSize());
    };

}
