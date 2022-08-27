package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    /**
     * Displays all tasks in the <Code>TaskList</Code>.
     *
     * @param tasks The <code>TaskList</code> object containing all stored tasks.
     * @param ui The <code>Ui</code> object
     * @param storage The database object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sendMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            ui.sendMessage((i + 1) + ". " + tasks.getTask(i));
        }
    }
}
