package bob.commands;

import bob.*;

import java.time.format.DateTimeParseException;

/**
 * UpdateCommand class to handle "update" keyword
 */
public class UpdateCommand extends Command{

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
