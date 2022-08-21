package duke.command;

import duke.*;

/**
 * Represents the list command to list all of the user's tasks.
 */
public class ListCommand extends Command {
    /**
     * Prints out list of tasks individually with index.
     * @param taskList List of tasks.
     * @param ui Ui interface for input and output.
     * @param storage Storage for Duke's file operations.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.printWithIndent("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.taskCount(); i++) {
            Task task = taskList.getTask(i);
            ui.printWithIndent(i + ". " + task);
        }
    }
}
