package roger.commands;

import roger.Storage;
import roger.TaskList;
import roger.Ui;
import roger.commands.Command;
import roger.tasks.Task;

/**
 * Encapsulates the command to delete a task.
 */
public class DeleteTaskCommand extends Command {
    protected int taskNum;

    /**
     * Create a DeleteTaskCommand.
     *
     * @param taskNum The taskNum of the task to be deleted.
     */
    public DeleteTaskCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Delete the task.
     *
     * @param tasks The TaskList of the Roger program.
     * @param ui The Ui used.
     * @param storage The storage to read and load to.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)  {
        Task task = tasks.delete(taskNum);
        ui.showcase("Haiya so lazy. Deleted this task:", task.toString());
        ui.show("Nephew now have " + Integer.toString(tasks.getLength()) + " tasks in the list.");
    }
}
