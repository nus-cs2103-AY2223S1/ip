package duke.commands;

import duke.ui.ListBox;
import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * Represents an executable command that marks the task as not done.
 *
 * @author sikai00
 */
public class UnmarkCommand extends Command {
    /** Command identifier used by Parser **/
    public static final String COMMAND_WORD = "unmark";
    private final int taskIndex;

    /**
     * Initializes a new UnmarkCommand instance.
     *
     * @param taskIndex Task index to be added
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) {
        // Checks for correct task index can only occur within the execution of the task.
        // Parser can only perform preliminary basic checks for correct input formats.
        if (this.taskIndex > taskList.size() - 1 || this.taskIndex < 0) {
            String msg = "Are you seriously not right in your head? There's no such task index!\n";
            return new CommandResult(msg);
        }
        taskList.unmarkTask(this.taskIndex);

        String msg = "Of course... I knew you've haven't really managed to get anything done... "
                + "I've marked this task as not done:\n ";
        ListBox lb = ListBox.getListBox(taskList.getTask(this.taskIndex));

        storage.writeAllToStorage(taskList);
        return new CommandResult(msg, lb);
    }
}
