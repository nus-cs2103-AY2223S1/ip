package duke.commands;

import duke.DukeException;
import duke.common.Messages;
import duke.storage.StorageFile;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Marks a task in the task list as done.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a task as done.\n"
            + "\tEx.: " + COMMAND_WORD + " <number>";

    private final int targetIndex;

    /**
     * Constructor for MarkCommand.
     *
     * @param targetIndex index of the task to be marked
     */
    public MarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(TaskList taskList, TextUi textUi, StorageFile storage) {
        try {
            Task task = taskList.markTask(targetIndex);
            storage.save(taskList);
            return new CommandResult(String.format(Messages.MESSAGE_TASK_UPDATE_STATUS + "\n%s", "done", task));
        } catch (DukeException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
