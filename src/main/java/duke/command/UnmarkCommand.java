package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * UnmarkCommand class represents the Unmark command given by the user.
 */
public class UnmarkCommand extends Command {
    private final int position;

    /**
     * Constructor of the UnmarkCommand class.
     * Represents the position of the task in taskList.
     *
     * @param position Position of the task in taskList.
     */
    public UnmarkCommand(int position) {
        this.position = position;
    }

    /**
     * Unmarks the task in the task list
     * and returns the message that the task has been removed.
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     * @return String representation of message of task being removed.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        Task unmarkedTask = taskList.getTask(position - 1);
        taskList.unmark(position - 1, storage);
        String commandMessage = "Congratulations! This task has been successfully unmarked!";
        return ui.displayCommandMessage(commandMessage, unmarkedTask, null);
    }
}
