package duke.commands;

import duke.common.Messages;
import duke.exceptions.InvalidInputException;
import duke.exceptions.UnableToSaveException;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Updates a task.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts task chronologically.\n"
            + "\tEx. " + COMMAND_WORD ;
    
    
    @Override
    public String execute(TaskList taskList, Ui ui, StorageFile storage) throws UnableToSaveException, InvalidInputException {
            taskList.sortTasks();
            storage.saveList(taskList);
            return Messages.MESSAGE_TASK_UPDATED;
    }

}
