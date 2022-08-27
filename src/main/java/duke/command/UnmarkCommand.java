package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/*
 * Encapsulates a command to mark a task as undone.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Creates a UnmarkCommand.
     * 
     * @param index Task ID of task to be marked as undone.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the UnmarkCommand to mark a Task as undone.
     * 
     * @param tasks TaskList containing task to be marked as undone.
     * @param ui Ui that displays success or error to user.
     * @param storage Persistent storage of task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String undoneTask = tasks.markAsUndone(index);
        storage.save(tasks);
        ui.printString("Task marked as undone:\n" + undoneTask);
    }
}
