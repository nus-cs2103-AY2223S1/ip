package duke.commands;

import duke.common.Messages;
import duke.exceptions.InvalidInputException;
import duke.exceptions.UnableToSaveException;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Delete a task in the task list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a task from the list.\n"
            + "\tEx. " + COMMAND_WORD;

    private final int taskNumber;

    /**
     * Constructor for DeleteCommand.
     *
     * @param taskNumber task index in tasklist
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

//    @Override
//    public void execute(
//            TaskList taskList, Ui ui, StorageFile storage) throws UnableToSaveException, InvalidInputException {
//        taskList.deleteTask(taskNumber);
//        storage.saveList(taskList);
//    }
    @Override
    public String execute(
            TaskList taskList, Ui ui, StorageFile storage) throws UnableToSaveException, InvalidInputException {
        taskList.deleteTask(taskNumber);
        storage.saveList(taskList);
        return Messages.MESSAGE_TASK_REMOVED;
    }
}
