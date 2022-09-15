package duke.commands;

import duke.ui.ListBox;
import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * Represents an executable command that marks the task as done.
 *
 * @author sikai00
 */
public class MarkCommand extends Command {
    /** Command identifier used by Parser **/
    public static final String COMMAND_WORD = "mark";
    private final int taskIndex;

    /**
     * Initializes a new MarkCommand instance.
     *
     * @param taskIndex Task index to be added
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) {
        if (this.taskIndex > taskList.size() - 1 || this.taskIndex < 0) {
            String msg = "Can't believe what an idiot I'm dealing with... There's no such task index!\n";
            return new CommandResult(msg);
        }
        taskList.markTask(this.taskIndex);

        String msg = "Wow, did you really complete a task? That's so unlike you... "
                + "I've marked your task as done:\n ";
        ListBox lb = ListBox.getListBox(taskList.getTask(this.taskIndex));

        storage.writeAllToStorage(taskList);
        return new CommandResult(msg, lb);
    }
}
