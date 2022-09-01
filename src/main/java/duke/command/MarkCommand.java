package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Mark Command, which marks a task as completed.
 *
 * @author Elgin
 */
public class MarkCommand extends Command {
    /** The arguments of the command (e.g. 'sleep /at 2020-12-12'). */
    private final String arguments;

    /**
     * Constructor of MarkCommand.
     *
     * @param arguments The arguments of the command.
     */
    public MarkCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the Mark command, and marks the task as done.
     *
     * @param tasks All tasks present in Duke.
     * @param ui The UI controller that handles interaction between user and Duke.
     * @param storage Storage that stores all tasks on Disk.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String taskDescription = tasks.markOrUnmark(this.arguments, true);

        return ui.taskMarkedMsg(taskDescription);
    }
}
