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
        if (this.taskIndex > taskList.size() - 1 || this.taskIndex < 0) {
            String msg = "There is no such task index... Try 'list' to view all the tasks and their index!";
            return new CommandResult(msg);
        }
        taskList.unmarkTask(this.taskIndex);

        String msg = "OK, I've marked this task as not done yet:\n ";
        ListBox lb = ListBox.getListBox(taskList.getTask(this.taskIndex));

        storage.writeAllToStorage(taskList);
        return new CommandResult(msg, lb);
    }
}
