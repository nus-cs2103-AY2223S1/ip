package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Task;

/*
Delete a task from the task list.
 */
public class DeleteCommand extends Command {

    String taskNumber;

    public DeleteCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the delete command.
     * @param taskList List that contains existing tasks.
     * @param storage Storage that stores tasks into a txt file.
     * @param ui Ui that generates response messages.
     * @return The response of Dukie.
     * @throws DukeException Exception that is specific to duke.
     */
    public String exec(TaskList taskList, Storage storage , Ui ui) throws DukeException {
        Task deletedTask = taskList.deleteTask(this.taskNumber);
        assert(ui != null);
        return ui.showDeleteMessage(deletedTask, taskList.getTaskListSize());
    };

}
