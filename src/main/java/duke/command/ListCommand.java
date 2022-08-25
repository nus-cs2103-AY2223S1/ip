package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the list command to list all the user's tasks.
 */
public class ListCommand extends Command {
    /**
     * Prints out list of tasks individually with index.
     * @param tasks List of tasks.
     * @param ui Ui interface for input and output.
     * @param storage Storage for Duke's file operations.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printWithIndent("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.taskCount(); i++) {
            Task task = tasks.getTask(i);
            ui.printWithIndent(i + ". " + task);
        }
    }
}
