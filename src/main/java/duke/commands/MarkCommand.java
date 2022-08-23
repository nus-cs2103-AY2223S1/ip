package duke.commands;

import duke.exceptions.InvalidInputException;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Marks a task in the task list as done.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a task as done.\n"
            + "\tEx.: " + COMMAND_WORD + " <number>";

    private final int taskNumber;

    /**
     * Constructor for MarkCommand.
     *
     * @param taskNumber index of the task to be marked
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, StorageFile storage) throws Exception {
        taskList.markTask(taskNumber);
        storage.saveList(taskList);
    }
}
