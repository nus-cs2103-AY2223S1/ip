package commands;

import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Command for deleting tasks.
 */
public class DeleteCommand extends Command {
    private int target;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns a new DeleteCommand.
     * @param tasks TaskList from which task will be deleted.
     * @param target Task to be deleted.
     * @param ui User Interface class that prints a message to the user.
     */
    public DeleteCommand(TaskList tasks, int target, Ui ui) {
        this.tasks = tasks;
        this.target = target;
        this.ui = ui;
    }

    /**
     * Executes the command, and returns a String
     * describing the execution of this Command.
     * @return A String describing the Task that was removed.
     */
    public String execute() {
        Task toRemove = tasks.get(target);
        tasks.remove(target);
        return ui.showRemoved(toRemove);
    }
}
