package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * The DeleteCommand class represents the delete command given by user.
 */
public class DeleteCommand extends Command {
    private final int position;

    /**
     * The constructor of the DeleteCommand class.
     * Sets the position of the task to be deleted
     * to the local variable.
     *
     * @param position Position of the task in taskList to be removed.
     */
    public DeleteCommand(int position) {
        this.position = position;
    }

    /**
     * Removes the task from the task list.
     * Returns the message that the task was removed.
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     * @return The message that task was removed.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        Task deletedTask = taskList.getTask(position - 1);
        taskList.remove(position - 1, storage);
        String commandMessage = "Noted! This task has been successfully removed!";
        return ui.displayCommandMessage(commandMessage, deletedTask, taskList.getSize());
    }
}
