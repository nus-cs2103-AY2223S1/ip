package duke.command;

import duke.*;

/**
 * Represents Command to delete any kind of task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Create delete command.
     * @param index Index of task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes task and prints to user.
     * Also saves the updated taskList to storage.
     * @param taskList List of tasks.
     * @param ui Ui interface for input and output.
     * @param storage Storage for Duke's file operations.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.deleteTask(index);
        ui.printWithIndent("Noted. I've removed this task:");
        ui.printWithIndent("  " + task);
        ui.printTaskCount(taskList.taskCount());
        storage.saveFile(taskList);
    }
}
