package roger.commands;

import roger.Storage;
import roger.TaskList;
import roger.Ui;
import roger.tasks.Task;

/**
 * Encapsulates the command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    protected int taskNum;

    /**
     * Create an UnmarkCommand.
     *
     * @param taskNum The taskNum of the task to be unmarked.
     */
    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Unmark the task as done.
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
        task.unmarkAsDone();
        ui.showcase("Haven't done yet, mark what mark? Unmarked this task:", task.toString());
    }
}

