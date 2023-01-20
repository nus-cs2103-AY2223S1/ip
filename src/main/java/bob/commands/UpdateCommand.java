package bob.commands;

import java.time.format.DateTimeParseException;

import bob.BobException;
import bob.Storage;
import bob.Task;
import bob.TaskList;
import bob.Ui;

/**
 * UpdateCommand class to handle "update" keyword
 */
public class UpdateCommand extends Command {

    private int index;
    private String updateInfo;

    /**
     * Constructor for UpdateCommand
     *
     * @param index index of task to be updated
     * @param updateInput name/date to update task with
     */
    public UpdateCommand(int index, String updateInput) {
        this.index = index;
        this.updateInfo = updateInput;
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage, Ui ui) throws BobException {
        try {
            Task task = taskList.getTask(this.index);
            String originalTask = task.toString();
            task.updateTask(this.updateInfo);
            storage.save(taskList);
            return ui.displayUpdateTask(taskList, task, originalTask);
        } catch (DateTimeParseException e) {
            throw new BobException("please input date in yyyy-mm-dd format!");
        }

    }
}
