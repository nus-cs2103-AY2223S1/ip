package duke.command;

import duke.CommandHistory;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

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
     * @param commandHistory History of commands made.
     * @return String representation of message of task being removed.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList,
            CommandHistory commandHistory) {
        Task unmarkedTask = taskList.getTask(position - 1);
        commandHistory.addCommand(this);
        taskList.unmark(position - 1, storage);
        String commandMessage = "Congratulations! This task has been successfully unmarked!";
        return ui.displayCommandMessage(commandMessage, unmarkedTask, null);
    }

    /**
     * Re-marks the task that has just been unmarked.
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     * @param commandHistory History of commands made.
     * @return The message that the task has been marked again.
     */
    @Override
    public String undoExecute(Ui ui, Storage storage, TaskList taskList, CommandHistory commandHistory) {
        Task markedTask = taskList.getTask(position - 1);
        taskList.mark(position - 1, storage);
        String markedMessage = "This task has been re-marked!";
        return ui.displayCommandMessage(markedMessage, markedTask, null);
    }
}
