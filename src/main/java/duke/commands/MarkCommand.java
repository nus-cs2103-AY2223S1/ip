package duke.commands;

import duke.common.Messages;
import duke.exceptions.InvalidInputException;
import duke.exceptions.UnableToSaveException;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Marks a task in the task list as done.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a task as done.\n"
            + "\tEx. " + COMMAND_WORD;

    private final int taskNumber;

    /**
     * Constructor for MarkCommand.
     *
     * @param taskNumber index of the task to be marked
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

//    @Override
//    public void execute(
//            TaskList taskList, Ui ui, StorageFile storage) throws UnableToSaveException, InvalidInputException {
//        taskList.markTask(taskNumber);
//        storage.saveList(taskList);
//    }

    @Override
    public String execute(
            TaskList taskList, Ui ui, StorageFile storage) throws UnableToSaveException, InvalidInputException {
        taskList.markTask(taskNumber);
        storage.saveList(taskList);
        return Messages.MESSAGE_TASK_MARKED;
    }
}
