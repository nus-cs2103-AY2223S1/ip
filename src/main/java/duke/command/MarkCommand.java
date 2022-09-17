package duke.command;

import duke.CommandHistory;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * MarkCommand class which represents the mark command given by the user.
 */
public class MarkCommand extends Command {
    private final int position;

    /**
     * Constructor of the MarkCommand class.
     * Sets the position of the task to be marked to the
     * local variable.
     *
     * @param position Position of the task to be marked.
     */
    public MarkCommand(int position) {
        this.position = position;
    }

    /**
     * Executes the mark command.
     * Marks the task in the task list.
     * Returns string representation of marked task.
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     * @param commandHistory History of commands made.
     * @return String representation of marked task.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList,
            CommandHistory commandHistory) {
        Task markedTask = taskList.getTask(position - 1);
        commandHistory.addCommand(this);
        taskList.mark(position - 1, storage);
        String commandMessage = "Congratulations! This task has been marked as done!";
        return ui.displayCommandMessage(commandMessage, markedTask, null);
    }

    /**
     * Unmarks task which has just been marked.
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     * @param commandHistory History of commands made.
     * @return The message that the task has been unmarked.
     */
    @Override
    public String undoExecute(Ui ui, Storage storage, TaskList taskList, CommandHistory commandHistory) {
        Task unmarkedTask = taskList.getTask(position - 1);
        taskList.unmark(position - 1, storage);
        String unmarkedMessage = "This task has just been unmarked!";
        return ui.displayCommandMessage(unmarkedMessage, unmarkedTask, null);
    }
}
