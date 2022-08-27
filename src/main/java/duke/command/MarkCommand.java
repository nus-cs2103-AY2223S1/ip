package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

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
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.printBorder();
        Task markedTask = taskList.getTask(position - 1);
        taskList.mark(position - 1, storage);
        String commandMessage = "Congratulations! This task has been marked as done!";
        ui.displayCommandMessage(commandMessage, markedTask, null);
        ui.printBorder();
    }
}
