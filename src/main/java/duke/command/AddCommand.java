package duke.command;

import duke.model.TaskList;
import duke.model.Task;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * A command to add tasks into a TaskList.
 */
public class AddCommand extends Command {

    private Task createdTask;

    /**
     * A constructor for a AddCommand.
     *
     * @param task the Task associated with the command.
     */
    public AddCommand(Task task) {
        createdTask = task;
    }

    /**
     * Executes a Command.
     *
     * @param taskList a list of tasks
     * @param storage a location to store the task information
     * @param ui a ui to handle user interactions
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.add(createdTask);
        storage.save(taskList);
        Ui.add(createdTask);
    }
}
