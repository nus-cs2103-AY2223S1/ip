package duke.command;

import duke.*;

/**
 * Represents command to unmark tasks as done.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Creates UnmarkCommand
     * @param index Index of task to mark as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks task as not done and prints it to user.
     * Also saves the updated taskList to storage.
     * @param taskList List of tasks.
     * @param ui Ui interface for input and output.
     * @param storage Storage for Duke's file operations.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.unmarkTask(index);
        ui.printWithIndent("OK, I've marked this task as not done yet:");
        ui.printWithIndent("  " + task);
        storage.saveFile(taskList);
    }
}
