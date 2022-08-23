package roger.commands;

import roger.Storage;
import roger.TaskList;
import roger.Ui;
import roger.tasks.Task;

/**
 * Encapsulates the command to mark a task as done.
 */
public class MarkCommand extends Command {
    protected int taskNum;

    /**
     * Create a MarkCommand.
     *
     * @param taskNum The taskNum of the task to be marked.
     */
    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Mark the task as done.
     *
     * @param tasks The TaskList of the Roger program.
     * @param ui The Ui used.
     * @param storage The storage to read and load to.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task;
        try {
            task = tasks.get(this.taskNum);
        } catch (IndexOutOfBoundsException e) {
            ui.show("That task does not exist!");
            return;
        }
        task.markAsDone();
        ui.showcase("Fuiyoh, nephew so efficient! Finished this task:", task.toString());
    }
}

