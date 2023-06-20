package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to add a task to the task list
 *
 * @author Pontakorn Prasertsuk
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Constructs a new AddCommand instance
     *
     * @param task the task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the AddCommand.
     *
     * @param taskList the task list to be added to
     * @param ui the user interface to be used
     * @param storage the storage to be used
     * @return output to be shown
     * @throws DukeException if an error occurs
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.add(task);
        ui.showOutput("Task has been added!: " + task.toString());
        ui.showOutput("Total tasks: " + taskList.getTaskList().size());
        storage.save(taskList.getTaskList());

        return "Task has been added!: " + task.toString() + "\n" + "Total tasks: "
                + taskList.getTaskList().size();
    }

    /**
     * Returns false as this is not the exit command
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
