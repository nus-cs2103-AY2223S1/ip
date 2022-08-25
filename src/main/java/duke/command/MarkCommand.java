package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command to mark tasks as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Creates mark command.
     * @param index Index of task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks task as done and prints it to user.
     * Also saves the updated taskList to storage.
     * @param taskList List of tasks.
     * @param ui Ui interface for input and output.
     * @param storage Storage for Duke's file operations.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.markTask(index);
        ui.printWithIndent("Nice! I've marked this task as done:");
        ui.printWithIndent("  " + task);
        storage.saveFile(taskList);
    }
}
