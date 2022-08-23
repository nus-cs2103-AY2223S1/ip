package roger.commands;

import roger.Storage;
import roger.TaskList;
import roger.Ui;
import roger.commands.Command;
import roger.tasks.Task;

/**
 * Encapsulates the command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * List all tasks.
     *
     * @param tasks The TaskList of the Roger program.
     * @param ui The Ui used.
     * @param storage The storage to read and load to.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.show("No tasks. Nephew must be a failure.");
            return;
        }

        ui.show("Nephew got a lot of things to do:");

        for (int taskNum = 1; taskNum < tasks.getLength() + 1; ++taskNum) {
            Task task = tasks.get(taskNum);
            ui.show(String.valueOf(taskNum) + ". " + task.toString());
        }
    }
}
