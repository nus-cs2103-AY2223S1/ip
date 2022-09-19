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
        StringBuilder stringBuilder = new StringBuilder("Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.getSize(); i++) {
            stringBuilder.append(i + 1).append(". ").append(tasks.getTask(i)).append('\n');
        }

        ui.sendMessage(stringBuilder.toString());
    }
}
