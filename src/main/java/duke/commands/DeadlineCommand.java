package duke.commands;

import duke.common.Messages;
import duke.exceptions.UnableToSaveException;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Adds a new deadline to the task list.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a new Deadline.\n"
            + "\tEx. " + COMMAND_WORD ;

    private final String description;
    private final String deadline;

    /**
     * Constructor for DeadlineCommand.
     *
     * @param description description of the new deadline
     * @param deadline date the deadline is to be completed by
     */
    public DeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

//    @Override
//    public void execute(TaskList taskList, Ui ui, StorageFile storage) throws UnableToSaveException {
//        taskList.addDeadlineTask(description, deadline);
//        storage.saveList(taskList);
//    }

    @Override
    public String execute(TaskList taskList, Ui ui, StorageFile storage) throws UnableToSaveException {
        taskList.addDeadlineTask(description, deadline);
        storage.saveList(taskList);
        return Messages.MESSAGE_TASK_ADDED;
    }
}